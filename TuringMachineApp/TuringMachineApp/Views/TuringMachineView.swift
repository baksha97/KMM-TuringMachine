//
//  TuringMachineView.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/4/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct TuringMachineView: View {

    @State private var showingAlert = false
    @State private var isHidingInitialInput = false

    @EnvironmentObject var vm: TuringMachineViewModel
    @SwiftUI.State var skipCount: Double = 1

    var initialInputView: some View {
        VStack {
            HStack {
                Text("Initial Input")
                    .bold()
                Spacer()
                Button(isHidingInitialInput ? "Unhide" : "Hide") {
                    isHidingInitialInput.toggle()
                }
            }
            if !isHidingInitialInput {
                HStack {
                    ForEach(vm.initialNumbers.indices, id: \.self) { index in
                        Capsule()
                            .fill(.blue.opacity(0.5))
                            .overlay(Text("\(vm.initialNumbers[index])"))
                            .frame(maxWidth: 48, maxHeight: 32)
                    }
                }
                HStack {
                    ScrollView {
                        VStack(alignment: .leading) {
                            Text(vm.rawProgramInput)
                                .lineLimit(nil)
                                .textSelection(.enabled)
                        }
                        .frame(maxWidth: .infinity)
                        .background(.blue.opacity(0.5))
                        .cornerRadius(18)

                    }
                }
            }

        }
    }

    var controlPanel: some View {
        VStack {
            HStack {
                Button("Reset") {
                    vm.reset()
                }
                .foregroundColor(.red)
                Spacer(minLength: 36)
                Slider(value: $skipCount, in: 1...1000)
                Spacer(minLength: 12)
                Button(skipCount == 1 ? "Next" : "Skip Next \(Int(skipCount))x") {
                    vm.execute(Int(skipCount))
                }
                .disabled(vm.isFinished)
            }
            HStack {
                Text("Execution Count: " + "\(vm.executionCount)")
                Spacer()
                Button("Count numbers on reel") {
                    showingAlert = true
                }
                .alert("Numbers on reel: \n\(vm.calculateNumbersOnReel().debugDescription)", isPresented: $showingAlert) {
                    Button("OK", role: .cancel) { }
                }
            }
        }
    }

    var body: some View {
        ScrollView {
            VStack(spacing: 24) {
                initialInputView.frame(maxHeight: 420)
                Divider()
                Text("Tape Reel").bold()
                TapeView()
                    .frame(maxHeight: 48)
                Text("Current State: " + vm.currentMachineState)
                    .bold()
                    .font(Font.title3)
                Text("Next Command: \(vm.nextCommand)")
                    .bold()
                    .font(Font.title3)
                Divider()
                if let message = vm.message {
                    Text(message)
                        .bold()
                }
                controlPanel
            }
        }
        .padding(12)
        .navigationTitle(Text(vm.machineName))
    }

}

// struct TuringMachineView_Previews: PreviewProvider {
//    static var previews: some View {
//        //        TuringMachineView()
//        TuringMachineView(TuringMachineView_Previews.makeFakeMachine()!)
//    }
// }
