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
    
    let tapeSize: Int
    
    init(tapeSize: Int, initialNumbers: [Int], program: String) {
        self.tapeSize = tapeSize
        vm = TuringMachineViewModel(tapeSize: tapeSize, initialNumbers: initialNumbers, program: program)
    }
    
    var body: some View {
        HStack {
            TapeView(turingMachineViewModel: vm)
            Spacer()
            VStack(){
                MachineInputView() { inputTapeSize, initialNumbers, programData in
                    print(inputTapeSize, initialNumbers, programData)
                    vm.reconfigureMachine(tapeSize: tapeSize, initialNumbers: initialNumbers, program: programData)
                }
                Divider()
                Spacer()
                Text(vm.message ?? "No message")
                Text("Current State: " + vm.currentMachineState).bold()
                Text("Execution count: \(vm.executionCount)")
                Button("Next") {
                    vm.executeNext()
                }
            }.padding(12)
        }
    }
}
