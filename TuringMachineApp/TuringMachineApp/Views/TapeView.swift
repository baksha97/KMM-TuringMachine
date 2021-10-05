//
//  TapeView.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/4/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct TapeView: View {
    
    @SwiftUI.State private var scrollTarget: Int?
    @ObservedObject var vm: TuringMachineViewModel
    
    
    init(turingMachineViewModel: TuringMachineViewModel) {
        self.vm = turingMachineViewModel
    }
    
    var body: some View {
            ScrollView {
                ScrollViewReader { reader in
                    HStack(spacing: 12) {
                        Divider()
                        VStack{
                            ForEach(vm.reel.indices, id: \.self) { index in
                                vm.currentIndex != index
                                ? Text(vm.reel[index].display)
                                : Text(vm.reel[index].display)
                                    .underline(true, color: .yellow)
                                    .bold()
                            }
                        }.onChange(of: vm.currentIndex) { target in
                            reader.scrollTo(target, anchor: .center)
                        }
                        Divider()
                    }.onAppear {
                        reader.scrollTo(vm.currentIndex)
                    }
                }
            }
        
    }
}
