export const residentList = [
    {
        "_id": 1,
        "first_name": "Alex",
        "last_name": "Podolich",
        "phone_number": "0675329590"
    },
    {
        "_id": 2,
        "first_name": "Artem",
        "last_name": "Sydorovych",
        "phone_number": "0985347172"
    },
    {
        "_id": 3,
        "first_name": "Azamat",
        "last_name": "Maraimbekov",
        "phone_number": "0951238485"
    }
]

export const residentDetailsList = [
    {
        "_id": 1,
        "first_name": "Alex",
        "last_name": "Podolich",
        "phone_number": "0675329590",
        "reservedRooms": [
            {
                "_id": 1,
                "start_date": "2023-01-01",
                "end_date": "2023-02-01",
                "room_id": 1,
                "res_id": 1,
                "room": {
                    "_id": 1,
                    "number": "204B",
                    "price": "2050"
                }
            }
        ]
    },
    {
        "_id": 2,
        "first_name": "Artem",
        "last_name": "Sydorovych",
        "phone_number": "0985347172",
        "reservedRooms": [
            {
                "_id": 2,
                "start_date": "2023-02-02",
                "end_date": "2023-03-02",
                "room_id": 1,
                "res_id": 2,
                "room": {
                    "_id": 1,
                    "number": "204B",
                    "price": "2050"
                }
            },
            {
                "_id": 3,
                "start_date": "2023-04-01",
                "end_date": "2023-05-01",
                "room_id": 2,
                "res_id": 2,
                "room": {
                    "_id": 2,
                    "number": "501VIP",
                    "price": "4080"
                }
            },
        ]
    },
    {
        "_id": 3,
        "first_name": "Azamat",
        "last_name": "Maraimbekov",
        "phone_number": "0951238485",
        "reservedRooms": [
            {
                "_id": 4,
                "start_date": "2023-05-10",
                "end_date": "2023-10-10",
                "room_id": 3,
                "res_id": 3,
                "room": {
                    "_id": 3,
                    "number": "322A",
                    "price": "3200"
                }
            },
            {
                "_id": 5,
                "start_date": "2023-10-10",
                "end_date": "2023-12-10",
                "room_id": 3,
                "res_id": 3,
                "room": {
                    "_id": 3,
                    "number": "322A",
                    "price": "3200"
                }
            },
        ]
    }
]