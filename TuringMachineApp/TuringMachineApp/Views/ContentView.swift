import SwiftUI
import Combine
import shared

struct ContentView: View {
    
    @SwiftUI.State private var bottomSheetShown = false
    
    var body: some View {
        NavigationView{
            HomeView()
        }
    }
    
    //    var body: some View {
    //        HStack{
    //            MachineInputView()
    //            TapeView(tapeSize: 60, initialNumbers: [2], program: x_squared)
    //        }
    //    }
}

struct HomeView: View {
    
    @SwiftUI.State private var showingSheet = false
    
    @SwiftUI.State private var machines: [TuringMachine] = []
    
    var body: some View {
        VStack{
            ForEach(machines.indices, id: \.self) { index in
                
                NavigationLink(destination: TuringMachineView(machines[index])) {
                    Capsule()
                        .fill(.cyan)
                        .overlay(
                            Text("Machine #\(index + 1)")
                                .foregroundColor(.white)
                        )
                        .frame(maxWidth: 400, maxHeight: 48)
                }
                
            }
            Text("Create new")
                .bold()
                .onTapGesture {
                    showingSheet.toggle()
                }
                .sheet(isPresented: $showingSheet) {
                    CreateMachineView() { machine in
                        machines.append(machine)
                    }
                }
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
