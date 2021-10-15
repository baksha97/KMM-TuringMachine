//
//  InfoContentView.swift
//  TuringMachineApp
//
//  Created by Brandon Cortes on 10/14/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

struct InfoContentView: View {
    
    let title: String
    let content: [String]
    
    var titleColor: Color = Color.teal
    var contentColor: Color = Color.black.opacity(0.75)
    
    // this does not work for some reason since:
    // Protocol 'TextSelectability' as a type cannot conform to the protocol itself
    // too lazy to fix - i will just always allow selectablity
    //    var textSelectablity : TextSelectability {
    //        return allowsContentSelection ? .disabled : .enabled
    //    }
    
    var body: some View {
        Text(title)
            .fontWeight(.black)
            .font(.subheadline)
            .padding(8)
            .background(titleColor)
            .clipShape(RoundedRectangle(cornerRadius: 8))
            .foregroundColor(Color.white)
        ForEach(content, id: \.self) { bullet in
            Text(bullet)
                .font(.subheadline)
                .padding(8)
                .background(contentColor)
                .clipShape(RoundedRectangle(cornerRadius: 8))
                .foregroundColor(Color.white)
                .multilineTextAlignment(.center)
                .lineSpacing(4)
                .textSelection(.enabled)
        }
    }
}

struct InfoContentView_Previews: PreviewProvider {
    static var previews: some View {
        InfoContentView(title: "test", content: ["1","2"])
    }
}
