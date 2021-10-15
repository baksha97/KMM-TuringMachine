//
//  CreateMachineViewModel.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Combine
import shared

// not giving the user the option to specify this param at this time. 
private let DEFAULT_TAPE_SIZE = 5000

class CreateMachineViewModel: ObservableObject {

    private let factory = MachineFactory()

    @Published var machineName: String = ""
    @Published var initialNumbers: [Int] = []
    @Published var programInput: String = ""

    @Published var errorMessage: String?

    func add(initialNumber: Int) {
        initialNumbers.append(initialNumber)
    }

    func makeMachine() -> TuringMachine? {

        guard !machineName.isEmpty else {
            errorMessage = "Please enter a name for the machine."
            return nil
        }

        guard !initialNumbers.isEmpty else {
            errorMessage = "Please at least one initial number for the machine."
            return nil
        }

        do {
            return try factory
                .makeTuringMachine(
                    name: machineName,
                    capacity: Int32(DEFAULT_TAPE_SIZE),
                    initialNumbers: initialNumbers.map { KotlinInt(integerLiteral: $0) },
                    programInput: programInput
                )
        } catch {
            errorMessage = error.localizedDescription
        }

        return nil
    }

}
