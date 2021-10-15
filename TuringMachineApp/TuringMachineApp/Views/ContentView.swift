import SwiftUI
import Combine

struct ContentView: View {

    @State private var bottomSheetShown = false

    var body: some View {
        NavigationView {
            HomeView()
        }
    }

}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
