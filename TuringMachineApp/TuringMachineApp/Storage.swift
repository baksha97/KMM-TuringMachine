//
//  Storage.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import shared

class SimpleMachineCache: ObservableObject {
    @Published var machines = [TuringMachine]() {
        didSet {
            let encoder = JSONEncoder()
            if let encoded = try? encoder.encode(machines.map { $0.asDTO() }) {
                UserDefaults.standard.set(encoded, forKey: "machines")
            }
        }
    }

    init() {
        let factory = MachineFactory()
        if let items = UserDefaults.standard.data(forKey: "machines") {
            let decoder = JSONDecoder()
            if let decoded = try? decoder.decode([TuringMachineDTO].self, from: items) {
                self.machines = decoded.compactMap { $0.asTuringMachine(factory: factory) }
                return
            }
        }

        self.machines = []
    }
}

extension TuringMachine {
    func deepCopy() -> TuringMachine {
        asDTO().asTuringMachine(factory: MachineFactory())!
    }
}

struct TuringMachineDTO: Identifiable, Codable {
    let id: String
    let name: String
    let tapeSize: Int
    let initialNumbers: [Int]
    let programInput: String
}

fileprivate extension TuringMachine {
    func asDTO() -> TuringMachineDTO {
        TuringMachineDTO(
            id: id,
            name: name,
            tapeSize: Int(initialTapeSize),
            initialNumbers: initialNumbers.map { Int(truncating: $0) },
            programInput: initialProgramInput
        )
    }
}

fileprivate extension TuringMachineDTO {
    func asTuringMachine(factory: MachineFactory) -> TuringMachine? {
        guard let machine = try? factory.makeTuringMachine(
            name: name,
            capacity: Int32(tapeSize),
            initialNumbers: initialNumbers.map { KotlinInt(integerLiteral: $0) },
            programInput: programInput
        ) else {
            return nil
        }

        return machine
    }
}
