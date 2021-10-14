//
//  AppInfoView.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/12/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

//fileprivate struct SectionHeaderStyle: ViewModifier {
//    let backgroundColor: Color
//    func body(content: Content) -> some View {
//        content
//            .font(.subheadline)
//            .padding(8)
//            .background(backgroundColor)
//            .clipShape(RoundedRectangle(cornerRadius: 8))
//            .foregroundColor(Color.white)
//    }
//}
//
//fileprivate struct SectionContentStyle: ViewModifier {
//    let backgroundColor: Color
//    func body(content: Content) -> some View {
//        content
//            .font(.subheadline)
//            .padding(8)
//            .background(backgroundColor)
//            .clipShape(RoundedRectangle(cornerRadius: 8))
//            .foregroundColor(Color.white)
//            .multilineTextAlignment(.center)
//            .lineSpacing(4)
//    }
//
//}

struct AppInfoView: View {
    
    var body: some View {
        ScrollView {
            VStack(spacing: 8) {
                
                BulletedSection(
                    title: "What does this thing do?",
                    content: details
                )
                
                Divider().frame(minHeight: 8)
                
                BulletedSection(
                    title: "How do I make a turing machine?",
                    content: howToCreate
                )
                
                Divider().frame(minHeight: 8)
                
                BulletedSection(
                    title: "How do I program a turing machine?",
                    content: howToCreate
                )
                
                UnbulletedSection(
                    title: "Sample Program (Add two numbers)",
                    content: sampleProgram
                )
                
                Divider().frame(minHeight: 8)
                
                UnbulletedSection(
                    title: "Special Thanks",
                    content: "Thank you to Dr. Ronald Fecter Ph.D. for inspiring me to create this application.",
                    titleColor: Color.blue,
                    contentColor: Color.black
                )
                
            }.padding(12)
        }.navigationTitle("Information")
    }
}

fileprivate var details = """
This program allows creation, loading, editing, and stepping through your own Turing Machine programs with the three commands:
Move left
Move right
Print (BLANK or ONE)
""".split(separator: "\n")

fileprivate var howToCreate = """
To create a new machine, you can click the create button on the My Machines page.
When creating a new machine, there are some limitations in place:
You can only set 5 initial numbers on tape. Each number can range a maximum of 12.
We do not have infinite tape on the device, so we are currently limited to 5000 cells of tape (including input).
""".split(separator: "\n")

fileprivate var howToProgram = """
Program input ignores/works around:
Empty lines (space out your work)
Lines containing "//" (comment or take out a line instead of deleting it)
Trailing/Leading white-space (so you don't have to worry about checking for white-space).
The quadruple states are treated as Strings; you do not need to use numbers for the states & you may append letters to numbers to make a valid state, for example: 1m,1,L,1c
""".split(separator: "\n")

fileprivate var sampleProgram = add_two

struct AppInfoView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView { AppInfoView() }
    }
}
