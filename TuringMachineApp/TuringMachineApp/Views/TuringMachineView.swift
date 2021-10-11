//
//  TuringMachineView.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/4/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TuringMachineView: View {
    
    @ObservedObject var vm: TuringMachineViewModel
    @SwiftUI.State var skipCount: Double = 1
    
    init(_ machine: TuringMachine) {
        vm = TuringMachineViewModel(machine: machine)
    }
    
    var machineInformation: some View {
        VStack {
            HStack {
                Text("Current State: ")
                    .bold()
                Spacer()
                Text(vm.currentMachineState)
            }
            
            HStack {
                Text("Next Command: ")
                    .bold()
                Spacer()
                Text("\(vm.nextCommand)")
            }
            
            HStack {
                Text("Execution Count: ")
                    .bold()
                Spacer()
                Text("\(vm.executionCount)")
            }
        }
    }
    
    var controlPanel: some View {
        VStack {
            HStack {
                Slider(value: $skipCount, in: 1...1000)
                Spacer(minLength: 12)
                Button(skipCount == 1 ? "Next" : "Next \(Int(skipCount))x") {
                    vm.skip(by: Int(skipCount))
//                    vm.executeNext()
                }
            }
        }
    }
    
    
    
    var body: some View {
        NavigationView {
            VStack(spacing: 24){
                TapeView(turingMachineViewModel: vm)
                Divider()
                machineInformation
                Text(vm.message ?? "")
                    .bold()
                controlPanel
            }
            .padding(12)
            .navigationTitle(Text("Run Machine"))
        }
    }
}

//struct TuringMachineView_Previews: PreviewProvider {
//    static var previews: some View {
//        //        TuringMachineView()
//        TuringMachineView(TuringMachineView_Previews.makeFakeMachine()!)
//    }
//}
