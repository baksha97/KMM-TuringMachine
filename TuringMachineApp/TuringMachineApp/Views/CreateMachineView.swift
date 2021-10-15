//
//  MachineInputView.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/4/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

typealias OnMachineInputComplete = (TuringMachine) -> Void

private let INITIAL_NUM_RANGE_MIN = 1.0
private let INITIAL_NUM_RANGE_MAX = 12.0
private let MAX_INITIAL_NUM_SIZE = 5

struct CreateMachineView: View {

    @Environment(\.dismiss)
    var dismiss

    @ObservedObject
    var viewModel: CreateMachineViewModel

    @SwiftUI.State
    private var newNumberToAdd = INITIAL_NUM_RANGE_MIN

    @SwiftUI.State private var showingAlert = false

    private var onMachineInputComplete: OnMachineInputComplete

    init(viewModel: CreateMachineViewModel = CreateMachineViewModel(), _ onMachineInputComplete: @escaping OnMachineInputComplete) {
        self.viewModel = viewModel
        self.onMachineInputComplete = onMachineInputComplete
    }

    var numbersView : some View {
        VStack {
            TextField("Name", text: $viewModel.machineName)
                .lineLimit(1)
                .padding(4)

            Text("Initial Numbers on Tape")
                .bold()

            HStack(alignment: .center) {
                ForEach(viewModel.initialNumbers.indices, id: \.self) { index in
                    Capsule()
                        .fill(.cyan)
                        .overlay(Text("\(viewModel.initialNumbers[index])").bold())
                        .onTapGesture {
                            viewModel.initialNumbers.remove(at: index)
                        }
                }
            }.frame(height: 36)

            HStack(alignment: .center) {
                Slider(value: $newNumberToAdd, in: INITIAL_NUM_RANGE_MIN...INITIAL_NUM_RANGE_MAX)
                Text("\(Int(newNumberToAdd))")
                Button(action: {
                    viewModel.add(initialNumber: Int(newNumberToAdd))
                    newNumberToAdd = INITIAL_NUM_RANGE_MIN
                }) {
                    HStack(spacing: 10) {
                        Image(systemName: "plus")
                    }
                }.disabled(viewModel.initialNumbers.count >= MAX_INITIAL_NUM_SIZE)
            }
        }
    }

    var body: some View {
        NavigationView {
            VStack(alignment: .center, spacing: 8) {
                numbersView
                Divider()
                Text("Program Input")
                    .bold()
                TextEditor(text: $viewModel.programInput)
                    .cornerRadius(10)
                    .colorMultiply(.gray)

            }
            .padding(12)
            .navigationBarTitle(Text("Machine Configuration"))
            .alert(viewModel.error ?? "", isPresented: $showingAlert) {

            }
            .navigationBarItems(
                leading:
                    Button("Cancel") {
                        dismiss()
                    },
                trailing:
                    Button("Create") {
                        if let machine = viewModel.makeMachine() {
                            onMachineInputComplete(machine)
                            dismiss()
                        } else {
                            showingAlert = true
                        }
                    }
            )
        }
    }

}

struct CreateMachineView_Previews: PreviewProvider {
    static var previews: some View {
        CreateMachineView { _ in

        }
    }
}
