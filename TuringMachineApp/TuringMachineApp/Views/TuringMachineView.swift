//
//  TuringMachineView.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/4/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct TuringMachineView: View {
    
    @ObservedObject var vm: TuringMachineViewModel
    
    init(initialNumbers: [Int], program: String) {
        vm = TuringMachineViewModel(initialNumbers: initialNumbers, program: program)
    }
    
    var body: some View {
        HStack {
            TapeView(turingMachineViewModel: vm)
            Spacer()
            VStack(){
                MachineInputView() { initialNumbers, programData in
                    print(initialNumbers, programData)
                    vm.reconfigureMachine(initialNumbers: initialNumbers, program: programData)
                }
                Divider()
                Spacer()
                Text(vm.message ?? "No message")
                Text("Current State: " + vm.currentMachineState).bold()
                Text("Execution count: \(vm.executionCount)")
                HStack {
                    Button("Skip 100x") {
                        vm.skip(by: 500)
                    }
                    Spacer()
                    Button("Next") {
                        vm.executeNext()
                    }
                }
            }.padding(12)
        }
    }
}
