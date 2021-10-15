//
//  TapeView.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/4/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct TapeView: View {

    @EnvironmentObject var vm: TuringMachineViewModel

    @Environment(\.colorScheme) var currentAppearenceMode

    var tapeTextColor: Color {
        currentAppearenceMode == .dark ? .black : .white
    }

    var body: some View {
        ScrollView(.horizontal) {
            ScrollViewReader { reader in
                LazyHStack {
                    ForEach(vm.reel.indices, id: \.self) { index in
                        VStack {
                            vm.currentIndex != index
                            ? Capsule()
                                .fill(.cyan)
                                .overlay(Text("\(vm.reel[index].display)").foregroundColor(tapeTextColor))
                                .frame(minWidth: 36, maxHeight: 36)
                            : Capsule()
                                .fill(.yellow)
                                .overlay(Text("\(vm.reel[index].display)").foregroundColor(tapeTextColor).bold())
                                .frame(minWidth: 36, maxHeight: 36)

                        }
                    }
                }.onChange(of: vm.currentIndex) { target in
                    reader.scrollTo(target, anchor: .center)
                }.onAppear {
                    reader.scrollTo(vm.currentIndex)
                }
            }
        }.frame(height: 120)

    }
}

// struct TapeView_Previews: PreviewProvider {
//    static var previews: some View {
//        TapeView()
//            .environmentObject(TuringMachineViewModel(TapeView_Previews.makeFakeMachine()))
//    }
//    
//    static func makeFakeMachine() -> TuringMachine {
//        let factory = MachineFactory()
//        let tape = try! factory.makeTape(capacity: Int32(30), initialNumbers: [3].map { KotlinInt(integerLiteral: $0) })
//        let program = try! factory.makeProgram(input: x_squared)
//        
//        return TuringMachine(name: "FAKE", tape: tape, program: program)
//    }
// }
