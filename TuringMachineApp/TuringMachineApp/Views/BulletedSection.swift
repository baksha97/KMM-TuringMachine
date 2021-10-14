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

struct BulletedSection: View {
    
    @State var title: String
    @State var content: Array<Substring>
    
    @State var titleColor: Color? = Color.teal
    @State var contentColor: Color? = Color.black.opacity(0.75)
    
    var body: some View {
        Group{
            Text(title)
                .fontWeight(.black)
                .modifier(SectionHeaderStyle(backgroundColor: titleColor!))
            ForEach(content, id: \.self) { bullet in
                Text(bullet)
                    .modifier(SectionContentStyle(backgroundColor: contentColor!))
            }
        }
    }
}

struct TextBlob_Previews: PreviewProvider {
    static var previews: some View {
        BulletedSection(title: "test", content: ["1","2"])
    }
}
