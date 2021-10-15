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

    init() { UITableView.appearance().backgroundColor = .clear }

    @Environment(\.colorScheme) var currentAppearenceMode

    var textColor: Color {
        currentAppearenceMode == .dark ? .black : .white
    }

    var body: some View {
        VStack {
            List {
                ForEach(cache.machines.indices, id: \.self) { index in
                    NavigationLink(destination: TuringMachineView().environmentObject(TuringMachineViewModel(cache.machines[index]))) {
                        Text(cache.machines[index].name)
                            .foregroundColor(textColor)
                            .bold()
                    }
                    .listRowBackground(Color.cyan)
                    .padding(8)
                    .swipeActions {
                        Button(role: .destructive) {
                            cache.machines.remove(at: index)
                        } label: {
                            Label("Delete", systemImage: "trash.fill")
                        }
                    }
                }
            }
            .cornerRadius(12)
            createNewCapsule

        }

        .navigationTitle("My Machines")
        .padding(12)
        .toolbar {
            ToolbarItemGroup(placement: .navigationBarTrailing) {
                NavigationLink(destination: AppInfoView()) {
                    Image(systemName: "info.circle.fill")
                }
            }
        }
    }

    var createNewCapsule: some View {
        Capsule()
            .fill(currentAppearenceMode == .dark ? .white : .black)
            .frame(maxWidth: 400, maxHeight: 48)
            .overlay(
                Text("Create +")
                    .bold()
                    .foregroundColor(textColor)
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
