//
//  CreateMachineViewModel.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Combine
import shared

private let DEFAULT_TAPE_SIZE = 5000

class CreateMachineViewModel: ObservableObject {

    private let factory = MachineFactory()

    @Published var machineName: String = ""
    @Published var initialNumbers: [Int] = []
    @Published var programInput: String = ""//add_two

    @Published var error: String?

    func add(initialNumber: Int) {
        initialNumbers.append(initialNumber)
    }

    func makeMachine() -> TuringMachine? {

        guard !machineName.isEmpty else {
            error = "Please enter a name for the machine."
            return nil
        }

        guard !initialNumbers.isEmpty else {
            error = "Please at least one initial number for the machine."
            return nil
        }

        guard let tape = try? factory.makeTape(capacity: Int32(DEFAULT_TAPE_SIZE), initialNumbers: initialNumbers.map { KotlinInt(integerLiteral: $0) }) else {
            error = "There was a problem with your tape input." // This should not happen because input must conform to the configuration on slider.
            return nil // .failure(CreateMachineError.cannotParseTape)
        }

        guard let program = try? factory.makeProgram(input: programInput) else {
            error = "There was a problem with your program input. Please review and try again."
            return nil // .failure(CreateMachineError.cannotParseProgram)
        }

        return TuringMachine(name: machineName, tape: tape, program: program)
    }

}
