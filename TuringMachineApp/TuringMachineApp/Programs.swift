//
//  Programs.swift
//  TuringMachineApp
//
//  Created by Travis Baksh on 10/4/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import shared

func makeFakeMachine() -> TuringMachine {
    let factory = MachineFactory()

    return try! factory
        .makeTuringMachine(
            name: "Test",
            capacity: 15,
            initialNumbers: [3].map { KotlinInt(integerLiteral: $0) },
            programInput: x_squared
        )
}

let x_squared = """
    1,B,R,299
    299,1,B,399
    399,B,R,499
    499,1,R,499
    499,B,R,599
    599,1,R,599
    599,B,1,699
    699,1,R,699
    699,B,L,799
    799,1,L,799
    799,B,L,899
    899,1,L,899
    899,B,L,999
    999,B,1,1099
    1099,1,R,1099
    1099,B,R,1199
    1199,1,1,299
    1199,B,L,1299
    1299,B,1,1399
    1399,1,L,1399
    1399,B,R,1499
    1499,1,B,199
    199,B,L,2

    1m,B,L,2
    2,B,1,3
    3,1,R,3
    3,B,R,4
    4,B,R,4
    4,1,R,5
    5,1,R,5
    5,B,R,6
    6,B,R,6
    6,1,R,7
    7,1,R,7
    7,B,R,8
    8,1,R,8
    8,B,1,9
    9,1,L,9
    9,B,L,10
    10,1,L,10
    10,B,L,19
    19,1,L,11
    19,B,L,19
    11,1,L,11
    11,B,R,12
    12,1,B,13
    13,B,R,14
    14,1,L,15
    15,B,L,15
    15,1,L,16
    16,1,L,16
    16,B,B,2
    14,B,R,17
    17,1,B,18
    17,B,R,27
    27,B,R,27
    18,B,R,29
    27,1,1,17
    29,B,L,22
    29,1,L,20
    20,B,L,20
    20,1,L,21
    21,1,L,21
    21,B,B,1m
    22,B,L,22
    22,1,B,23
    23,B,L,24
    24,1,1,22
    24,B,R,25
    25,B,R,25
    25,1,L,26
""".trimmingCharacters(in: .whitespacesAndNewlines)
    .replacingOccurrences(of: "    ", with: "")

let add_two = """
// REMEMBER: Comments are allowed!

//  --- BEGIN PROGRAM

// always starts on State "1"
// move to the next blank
1,B,R,2
// move onto the first number
// remove one from the first number
2,1,B,3
// move onto the number again
3,B,R,4
// move all the way to the end of first number
4,1,R,4
// now we're at the end of the first number, replace the seperator with the removed one
4,B,1,5
// move back to the front of the first number (how machines conventionally end)
5,1,L,5
// now we are in front of the sum of the numbers we started with - as one number.
""".trimmingCharacters(in: .whitespacesAndNewlines)
    .replacingOccurrences(of: "    ", with: "")
