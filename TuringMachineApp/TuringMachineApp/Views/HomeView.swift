//
//  HomeView.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct HomeView: View {

    @State private var showingSheet = false

    @ObservedObject private var cache: SimpleMachineCache = SimpleMachineCache()

    var body: some View {
        ScrollView {
            VStack(spacing: 12) {
                ForEach(cache.machines.indices, id: \.self) { index in
                    HStack {
                        NavigationLink(destination: TuringMachineView(TuringMachineViewModel(cache.machines[index]))) {
                            Capsule()
                                .fill(.cyan)
                                .overlay(
                                    HStack {
                                        Text(cache.machines[index].name)
                                            .foregroundColor(.white)
                                        Spacer()
                                        Button(role: .destructive) {
                                            cache.machines.remove(at: index)
                                        } label: {
                                            Label("", systemImage: "trash")
                                        }
                                    }.padding(24)
                                )
                                .frame(maxWidth: 400, minHeight: 48, maxHeight: 48)
                        }
                    }
                }
                createNewCapsule
            }
        }.navigationTitle("My Machines")
            .padding(12)
    }

    var createNewCapsule: some View {
        Capsule()
            .fill(.black)
            .frame(maxWidth: 400, maxHeight: 48)
            .overlay(
                Text("Create new")
                    .bold()
                    .foregroundColor(.white)
            )                .onTapGesture {
                showingSheet.toggle()
            }
            .sheet(isPresented: $showingSheet) {
                CreateMachineView { machine in
                    cache.machines.append(machine)
                }
            }
            .frame(maxWidth: 400, minHeight: 48, maxHeight: 48)
    }
}
