import SwiftUI
import Combine



struct ContentView: View {
    
    @State private var bottomSheetShown = false
    
    var body: some View {
        TuringMachineView(initialNumbers: [7], program: x_squared)
    }
    
//    var body: some View {
//        HStack{
//            MachineInputView()
//            TapeView(tapeSize: 60, initialNumbers: [2], program: x_squared)
//        }
//    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
