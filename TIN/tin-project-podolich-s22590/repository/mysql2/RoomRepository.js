const db = require('../../config/mysql2/db');

const roomSchema = require('../../model/joi/Room');

exports.getRooms = () => {
    return db.promise().query('SELECT * FROM Room')
        .then((results, fields) => {
            console.log(results[0]);
            return results[0];
        })

    .catch(err => {
        console.log(err);
        throw err;
    })
};

exports.getRoomById = (roomId) => {
    const query = `SELECT room._id as _id, room.number, room.price, resRoom._id as resRoom_id,
    resRoom.start_date, resRoom.end_date, r._id as res_id, r.first_name, r.last_name, r.phone_number
    FROM Room room
    left join ReservedRoom resRoom on resRoom.room_id = room._id
    left join Resident r on resRoom.res_id = r._id
    where room._id = ?`

    return db.promise().query(query, [roomId])
        .then((results, fields) => {
            const firstRow = results[0][0];
            if (!firstRow) {
                return {};
            }

            const room = {
                _id: parseInt(roomId),
                number: firstRow.number,
                price: firstRow.price,
                reservedRooms: []
            }
            for (let i = 0; i < results[0].length; i++) {
                const row = results[0][i];
                if (row.resRoom_id) {
                    const reservedRoom = {
                        _id: row.resRoom_id,
                        start_date: row.start_date,
                        end_date: row.end_date,
                        resident: {
                            _id: row.res_id,
                            first_name: row.first_name,
                            last_name: row.last_name,
                            phone_number: row.phone_number,
                        }
                    };
                    room.reservedRooms.push(reservedRoom);
                }
            }
            return room;
        })
        .catch(err => {
            console.log(err);
            throw err;
        })
};

exports.createRoom = (newRoomData) => {
    const vRes = roomSchema.validate(newRoomData, { abortEarly: false });
    if (vRes.error) {
        return Promise.reject(vRes.error);
    }
    const number = newRoomData.number;
    const price = newRoomData.price;

    const sql = 'INSERT into Room (number, price) VALUES (?,?)';
    return db.promise().execute(sql, [number, price]);
};

exports.updateRoom = (roomId, roomData) => {
    const vRes = roomSchema.validate(roomData, { abortEarly: false });
    if (vRes.error) {
        return Promise.reject(vRes.error);
    }
    const number = roomData.number;
    const price = roomData.price;

    const sql = `UPDATE Room set number = ?, price = ? where _id = ?`;

    return db.promise().execute(sql, [number, price, roomId]);
};

exports.deleteRoom = (roomId) => {
    const sql1 = `DELETE FROM ReservedRoom WHERE room_id = ?`;
    const sql2 = `DELETE FROM Room WHERE _id = ?`;

    return db.promise().execute(sql1, [roomId])
        .then(() => {
            return db.promise().execute(sql2, [roomId]);
        });
};