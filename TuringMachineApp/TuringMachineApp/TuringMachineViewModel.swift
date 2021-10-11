//
//  TuringMachine.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/4/21.
//  Copyright © 2021 orgName. All rights reserved.
//

//import Combine
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
    
    @Published var currentIndex: Int
    @Published var executionCount: Int
    @Published var reel: [ReelItemUiModel]
    @Published var currentMachineState: String
    @Published var message: String?
    
    var nextCommand: String {
        guard let next = machine.nextQuadruple() else { return "" }
        switch next.command {
        case .blank:
            return ""
        case .fill:
            return ""
        case.left:
            return ""
        case .right:
            return ""
        default:
            fatalError("Invalid Command Type: KMM Not parsed.")
        }
    }
    
    init(machine: TuringMachine) {
        self.machine = machine
        reel = machine.reel
            .map { $0 as! KotlinInt }
            .map { ReelItemUiModel($0) }
        
        currentIndex = Int(machine.reelPosition)
        currentMachineState = machine.currentTapeState.name
        executionCount = Int(machine.executions)
    }
    
    func skip(by skipCount: Int) {
        for _ in 0..<skipCount {
            executeNext()
        }
    }
    
    func executeNext() {
        if machine.hasNextQuadruple() {
            let result = machine.executeSubsequentQuadruple()
            executionCount = Int(machine.executions)
            switch result {
            case let success as TapeProcessResult.MovementSuccess:
                currentMachineState = success.endingState.name
                currentIndex = Int(success.newPosition)
            case let success as TapeProcessResult.WriteSuccess:
                currentMachineState = success.endingState.name
                currentIndex = Int(success.index)
                reel[currentIndex] = ReelItemUiModel(machine.reel[currentIndex] as! KotlinInt)
            case let failure as TapeProcessResult.MovementFailure:
                message = "There is not enough tape initialized in this machine to continue."
                print(failure)
            default:
                message = "Error! Execution has not returned a tape process result."
            }
            
            message = "Executing..."
            
        } else {
            message = "There are no quadruples to execute."
        }
    }
}
