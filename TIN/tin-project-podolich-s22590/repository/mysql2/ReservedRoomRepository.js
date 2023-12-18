const db = require('../../config/mysql2/db');

const reservedRoomSchema = require('../../model/joi/ReservedRoom');

exports.getReservedRooms = () => {
    const query = `SELECT resRoom._id as resRoom_id, resRoom.start_date, resRoom.end_date, room._id as room_id, room.number, room.price,
    r._id as res_id, r.first_name, r.last_name, r.phone_number
    FROM ReservedRoom resRoom
    left join Resident r on resRoom.res_id = r._id
    left join Room room on resRoom.room_id = room._id`
    return db.promise().query(query)
        .then((results, fields) => {
            const resRooms = [];
            for (let i = 0; i < results[0].length; i++) {
                const row = results[0][i];
                const resRoom = {
                    _id: row.resRoom_id,
                    start_date: row.start_date,
                    end_date: row.end_date,
                    room: {
                        _id: row.room_id,
                        number: row.number,
                        price: row.price
                    },
                    resident: {
                        _id: row.res_id,
                        first_name: row.first_name,
                        last_name: row.last_name,
                        phone_number: row.phone_number,
                        password: row.password
                    }
                };
                resRooms.push(resRoom);
            }
            console.log(resRooms);
            return resRooms;
        })

    .catch(err => {
        console.log(err);
        throw err;
    })
};

exports.getReservedRoomById = (resRoomId) => {
    const query = `SELECT resRoom._id as resRoom_id, resRoom.start_date, resRoom.end_date, room._id as room_id, room.number, room.price,
    r._id as res_id, r.first_name, r.last_name, r.phone_number
    FROM ReservedRoom resRoom
    left join Resident r on resRoom.res_id = r._id
    left join Room room on resRoom.room_id = room._id
    where resRoom._id = ?`

    return db.promise().query(query, [resRoomId])
        .then((results, fields) => {
            const row = results[0][0];
            if (!row) {
                return {};
            }

            const resRoom = {
                _id: resRoomId,
                start_date: row.start_date,
                end_date: row.end_date,
                room: {
                    _id: row.room_id,
                    number: row.number,
                    price: row.price
                },
                resident: {
                    _id: row.res_id,
                    first_name: row.first_name,
                    last_name: row.last_name,
                    phone_number: row.phone_number,
                    password: row.password
                }
            };
            console.log(resRoom);
            return resRoom;
        })
        .catch(err => {
            console.log(err);
            throw err;
        })
};

exports.createReservedRoom = (data) => {
    const vRes = reservedRoomSchema.validate(data, { abortEarly: false });
    if (vRes.error) {
        return Promise.reject(vRes.error);
    }
    console.log('create Reserved Room');
    console.log(data);

    const start_date = data.start_date;
    const end_date = data.end_date;
    const room_id = data.room_id;
    const res_id = data.res_id;

    const sql = 'INSERT into ReservedRoom (start_date, end_date, room_id, res_id) VALUES (?,?,?,?)';
    return db.promise().execute(sql, [start_date, end_date, room_id, res_id]);
};

exports.updateReservedRoom = (reservedRoomId, data) => {
    const vRes = reservedRoomSchema.validate(data, { abortEarly: false });
    if (vRes.error) {
        return Promise.reject(vRes.error);
    }
    console.log('update Reserved Room');
    console.log(data);
    data.end_date = data.end_date ? data.end_date : null;

    const start_date = data.start_date;
    const end_date = data.end_date;
    const room_id = data.room_id;
    const res_id = data.res_id;


    const sql = `UPDATE ReservedRoom set start_date = ?, end_date = ?, room_id = ?, res_id = ? where _id = ?`;
    return db.promise().execute(sql, [start_date, end_date, room_id, res_id, reservedRoomId]);
};

exports.deleteManyReservedRoom = (reservedRoomIds) => {
    const sql = `DELETE FROM ReservedRoom WHERE _id IN (?)`;

    return db.promise().execute(sql, [reservedRoomIds])
};