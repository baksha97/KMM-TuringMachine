//
//  TextBlob.swift
//  TuringMachineApp
//
//  Created by Brandon Cortes on 10/14/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

fileprivate struct SectionHeaderStyle: ViewModifier {
    let backgroundColor: Color
    func body(content: Content) -> some View {
        content
            .font(.subheadline)
            .padding(8)
            .background(backgroundColor)
            .clipShape(RoundedRectangle(cornerRadius: 8))
            .foregroundColor(Color.white)
    }
}

fileprivate struct SectionContentStyle: ViewModifier {
    let backgroundColor: Color
    func body(content: Content) -> some View {
        content
            .font(.subheadline)
            .padding(8)
            .background(backgroundColor)
            .clipShape(RoundedRectangle(cornerRadius: 8))
            .foregroundColor(Color.white)
            .multilineTextAlignment(.center)
            .lineSpacing(4)
    }
    
}

struct UnbulletedSection: View {
    
    @State var title: String
    @State var content: String
    
    @State var titleColor: Color = Color.teal
    @State var contentColor: Color = Color.black.opacity(0.75)
    
    var body: some View {
        Group{
            Text(title)
                .fontWeight(.black)
                .modifier(SectionHeaderStyle(backgroundColor: titleColor))
            
            Text(content)
                .modifier(SectionContentStyle(backgroundColor: contentColor))
            
        }
    }
}

struct UnbulletedSection_Previews: PreviewProvider {
    static var previews: some View {
        UnbulletedSection(title: "test", content: "tester")
    }
}
