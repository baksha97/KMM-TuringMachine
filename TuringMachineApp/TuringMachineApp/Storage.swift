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
                self.machines = decoded.map { $0.asTuringMachine(factory: factory) }
                return
            }
        }

        self.machines = []
    }
}

struct TuringMachineDTO: Identifiable, Codable {
    let id = UUID()
    let tapeSize: Int
    let initialNumbers: [Int]
    let programInput: String
    let name: String

    init(name: String, tapeSize: Int, initialNumbers: [Int], program: String) {
        self.name = name
        self.tapeSize = tapeSize
        self.initialNumbers = initialNumbers
        self.programInput = program
    }
}

extension TuringMachine {
    func asDTO() -> TuringMachineDTO {
        TuringMachineDTO(name: name, tapeSize: tape.reel.count, initialNumbers: tape.initialNumbers.map { Int(truncating: $0) }, program: program.rawInput)
    }
}

extension TuringMachineDTO {
    func asTuringMachine(factory: MachineFactory) -> TuringMachine {

        let tape = try! factory.makeTape(capacity: Int32(tapeSize), initialNumbers: initialNumbers.map { KotlinInt(integerLiteral: $0) })

        let program = try! factory.makeProgram(input: programInput)

        return TuringMachine(name: name, tape: tape, program: program)
    }
}
