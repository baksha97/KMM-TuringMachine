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
    
    @SwiftUI.State private var scrollTarget: Int?
    @ObservedObject var vm: TuringMachineViewModel
    
    
    init(turingMachineViewModel: TuringMachineViewModel) {
        self.vm = turingMachineViewModel
    }
    
    var body: some View {
        ScrollView(.horizontal) {
            ScrollViewReader { reader in
                LazyHStack{
                    ForEach(vm.reel.indices, id: \.self) { index in
                        vm.currentIndex != index
                        ? Capsule()
                            .fill(.cyan)
                            .overlay(Text("\(vm.reel[index].display)"))
                            .frame(minWidth: 36, maxHeight: 36)
                        : Capsule()
                            .fill(.yellow)
                            .overlay(Text("\(vm.reel[index].display)").bold())
                            .frame(minWidth: 36, maxHeight: 36)
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
