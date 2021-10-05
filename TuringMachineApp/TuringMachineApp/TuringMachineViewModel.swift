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


struct ReelItem: Identifiable {
    
    let rawValue: KotlinInt
    let display: String
    let id = UUID()
    
    init(_ rawValue: KotlinInt) {
        self.rawValue = rawValue
        display = rawValue == KotlinInt(0) ? "🅱️" : "1️⃣"
    }
}

class TuringMachineViewModel: ObservableObject {
    
    private let factory = MachineFactory()
    
    private var machine: TuringMachine
    
    @Published var currentIndex: Int = 0
    @Published var executionCount: Int = 0
    @Published var reel: [ReelItem]// = [ReelItem(0)]
    @Published var currentMachineState: String
    @Published var message: String?
    
    
    init(tapeSize: Int, initialNumbers: [Int], program: String) {
        machine = TuringMachine(
            tape: factory.makeTape(capacity: Int32(tapeSize), initialNumbers: initialNumbers.map{ KotlinInt(integerLiteral: $0) }),
            program: factory.makeProgram(input: program)
        )
        
        
        reel =
        machine.reel
            .map { $0 as! KotlinInt }
            .map { ReelItem($0) }
        
        currentIndex = Int(machine.reelPosition)
        currentMachineState = machine.currentTapeState.name
    }
    
    func reconfigureMachine(tapeSize: Int, initialNumbers: [Int], program: String){
        machine = TuringMachine(
            tape: factory.makeTape(capacity: Int32(tapeSize), initialNumbers: initialNumbers.map{ KotlinInt(integerLiteral: $0) }),
            program: factory.makeProgram(input: program)
        )
        
        
        reel =
        machine.reel
            .map { $0 as! KotlinInt }
            .map { ReelItem($0) }
        
        currentIndex = Int(machine.reelPosition)
        currentMachineState = machine.currentTapeState.name
        executionCount = Int(machine.executions)
    }
    
    func executeNext() {
        if machine.hasSubsequentState() {
            let result = machine.executeSubsequentQuadruple()
            executionCount = Int(machine.executions)
            switch result {
            case let success as TapeProcessResult.MovementSuccess:
                currentMachineState = success.endingState.name
                currentIndex = Int(success.newPosition)
            case let success as TapeProcessResult.WriteSuccess:
                currentMachineState = success.endingState.name
                currentIndex = Int(success.index)
                reel[currentIndex] = ReelItem(machine.reel[currentIndex] as! KotlinInt)
            case let failure as TapeProcessResult.MovementFailure:
                message = "There is not enough tape initialized in this machine to continue."
                print(failure)
            default:
                message = "Error! Execution is not returning a tape process result."
            }
            
            message = "Executing..."
            
        } else {
            message = "No work to do."
        }
        
    }
}
