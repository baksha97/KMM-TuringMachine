//
//  TuringMachine.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/4/21.
//  Copyright © 2021 orgName. All rights reserved.
//

// import Combine
import SwiftUI
import shared

struct ReelItemUiModel: Identifiable {

    let rawValue: KotlinInt
    let display: String
    let id = UUID()

    init(_ rawValue: KotlinInt) {
        self.rawValue = rawValue
        display = rawValue == KotlinInt(0) ? "B" : "1"
//        display = rawValue == KotlinInt(0) ? "🅱️" : "1️⃣"
    }
}

class TuringMachineViewModel: ObservableObject {

    private let factory = MachineFactory()

    private var machine: TuringMachine
    private var initialMachineCache: TuringMachine

    @Published var currentIndex: Int
    @Published var executionCount: Int
    @Published var reel: [ReelItemUiModel]
    @Published var currentMachineState: String
    @Published var nextCommand: String
    @Published var message: String?
    @Published var isFinished: Bool

    var rawProgramInput: String {
        return machine.initialProgramInput
    }

    var initialNumbers: [Int] {
        return machine.initialNumbers.map(Int.init(truncating:))
    }

    var machineName: String {
        return machine.name
    }

    init(_ machine: TuringMachine) {
        self.machine = machine
        self.initialMachineCache = machine.deepCopy()
        reel = machine.reel
            .map { ReelItemUiModel($0) }

        currentIndex = Int(machine.reelPosition)

        currentMachineState = machine.currentStateName
        nextCommand = machine.nextQuadruple()?.command.iconToDisplay() ?? "None"
        executionCount = Int(machine.executions)
        isFinished = !machine.hasNextQuadruple()
        message = nil
    }

    func reset() {
        machine = initialMachineCache.deepCopy()
        reel = machine.reel
            .map { ReelItemUiModel($0) }

        currentIndex = Int(machine.reelPosition)
        currentMachineState = machine.currentStateName
        nextCommand = machine.nextQuadruple()?.command.iconToDisplay() ?? "None"
        executionCount = Int(machine.executions)
        isFinished = !machine.hasNextQuadruple()
        message = nil
    }

    func execute(_ skipCount: Int) {
        for _ in 0..<skipCount {
            executeNext()
        }
    }

    private func executeNext() {
        if machine.hasNextQuadruple() {
            let result = machine.executeSubsequentQuadruple()
            executionCount = Int(machine.executions)
            nextCommand = machine.nextQuadruple()?.command.iconToDisplay() ?? "None"
            isFinished = !machine.hasNextQuadruple()
            switch result {
            case let success as TapeProcessResult.MovementSuccess:
                currentMachineState = success.endingState
                currentIndex = Int(success.newPosition)
            case let success as TapeProcessResult.WriteSuccess:
                currentMachineState = success.endingState
                currentIndex = Int(success.index)
                reel[currentIndex] = ReelItemUiModel(machine.reel[currentIndex])
            case let failure as TapeProcessResult.MovementFailure:
                message = "There is not enough tape initialized in this machine to continue."
                print(failure)
            default:
                message = "Error! Execution has not returned a tape process result."
            }

            message = nil

        } else {
            message = "There are no quadruples to execute."
        }
    }

    func calculateNumbersOnReel() -> [Int] {
        return machine.currentIntegersOnReel.map(Int.init(truncating:))
    }
}

fileprivate extension Command {
    func iconToDisplay() -> String {
        switch self {
        case .blank:
            return "🅱️"
        case .fill:
            return "1️⃣"
        case.left:
            return "⬅️"
        case .right:
            return "➡️"
        default:
            return "ERROR"
        }
    }
}
