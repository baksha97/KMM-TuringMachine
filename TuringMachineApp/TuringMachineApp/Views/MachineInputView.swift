//
//  MachineInputView.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/4/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

typealias OnMachineInputComplete = ([Int], String) -> Void

struct MachineInputView: View {
    
    @SwiftUI.State private var programInput: String = ""
    @SwiftUI.State private var initialNumbers: String = "1"

    private var onMachineInputComplete: OnMachineInputComplete
    
    init(_ onMachineInputComplete: @escaping OnMachineInputComplete){
        self.onMachineInputComplete = onMachineInputComplete
    }
    
    var body: some View {
        VStack {
            Spacer()
//            HStack{
//                Text("Tape size")
//                TextField("Input Tape Size", text: $tapeSizeValue)
//                    .keyboardType(.numberPad)
//                    .padding()
//                    .overlay(RoundedRectangle(cornerRadius: 8).stroke(Color.gray, lineWidth: 1))
//            }
            HStack{
                Text("Initial numbers")
                TextField("Initial Numbers", text: $initialNumbers)
                    .keyboardType(.numberPad)
                    .padding()
                    .overlay(RoundedRectangle(cornerRadius: 8).stroke(Color.gray, lineWidth: 1))
            }
            Divider()
            TextEditor(text: $programInput)
                .accentColor(.red.opacity(12))
            Spacer()
            Button("Configure Turing Machine") {
                onMachineInputComplete(initialNumbers.split(separator: ",").compactMap{ Int($0) }, programInput)
            }
        }
    }
    
}
