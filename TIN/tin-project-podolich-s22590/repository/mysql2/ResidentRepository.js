const db = require('../../config/mysql2/db');
const authUtils = require('../../utils/authUtils');

const residentSchema = require('../../model/joi/Resident');
const residentCreateSchema = require('../../model/joi/ResidentCreate');
exports.getResidents = () => {
    return db.promise().query('SELECT * FROM Resident')
        .then((results, fields) => {
            console.log(results[0]);
            return results[0];
        })

    .catch(err => {
        console.log(err);
        throw err;
    })
};

exports.getResidentById = (residentId) => {
    const query = `SELECT r._id as _id, r.first_name, r.last_name, r.phone_number, r.password, resRoom._id as resRoom_id,
    resRoom.start_date, resRoom.end_date, room._id as room_id, room.number, room.price
    FROM Resident r
    left join ReservedRoom resRoom on resRoom.res_id = r._id
    left join Room room on resRoom.room_id = room._id
    where r._id = ?`

    return db.promise().query(query, [residentId])
        .then((results, fields) => {
            const firstRow = results[0][0];
            if (!firstRow) {
                return {};
            }

            const resident = {
                _id: parseInt(residentId),
                first_name: firstRow.first_name,
                last_name: firstRow.last_name,
                phone_number: firstRow.phone_number,
                password: firstRow.password,
                reservedRooms: []
            }
            for (let i = 0; i < results[0].length; i++) {
                const row = results[0][i];
                if (row.resRoom_id) {
                    const reservedRoom = {
                        _id: row.resRoom_id,
                        start_date: row.start_date,
                        end_date: row.end_date,
                        room: {
                            _id: row.room_id,
                            number: row.number,
                            price: row.price
                        }
                    };
                    resident.reservedRooms.push(reservedRoom);
                }
            }
            return resident;
        })
        .catch(err => {
            console.log(err);
            throw err;
        })
};

exports.createResident = (newResidentData) => {
    const vRes = residentCreateSchema.validate(newResidentData, { abortEarly: false });
    if (vRes.error) {
        return Promise.reject(vRes.error);
    }
    return checkPhoneUnique(newResidentData.phone_number)
        .then(phone_number_error => {
            if (Object.entries(phone_number_error).length !== 0) {
                return Promise.reject(phone_number_error);
            } else {

                const first_name = newResidentData.first_name;
                const last_name = newResidentData.last_name;
                const phone_number = newResidentData.phone_number;
                const password = newResidentData.password;

                const hashedPassword = authUtils.hashPassword(password);

                const sql = `INSERT into Resident (first_name, last_name, phone_number, password) VALUES (?,?,?,?)`;
                return db.promise().execute(sql, [first_name, last_name, phone_number, hashedPassword]);
            }
        })
        .catch(err => {
            return Promise.reject(err);
        });
};

exports.updateResident = (residentId, residentData) => {
    const vRes = residentSchema.validate(residentData, { abortEarly: false });
    if (vRes.error) {
        return Promise.reject(vRes.error);
    }
    return checkPhoneUnique(residentData.phone_number, residentId)
        .then(phone_number_error => {
            if (Object.entries(phone_number_error).length !== 0) {
                return Promise.reject(phone_number_error);
            } else {
                const first_name = residentData.first_name;
                const last_name = residentData.last_name;
                const phone_number = residentData.phone_number;
                const password = residentData.password;

                const hashedPassword = authUtils.hashPassword(password);
                var sql;
                if(password == ""){
                    sql = `UPDATE Resident set first_name = ?, last_name = ?, phone_number = ? where _id = ?`;
                    return db.promise().execute(sql, [first_name, last_name, phone_number, residentId]);
                }else{
                    sql = `UPDATE Resident set first_name = ?, last_name = ?, phone_number = ?, password = ? where _id = ?`;
                    return db.promise().execute(sql, [first_name, last_name, phone_number, hashedPassword, residentId]);
                }
            }
        })
        .catch(err => {
            return Promise.reject(err);
        });
    // const first_name = residentData.first_name;
    // const last_name = residentData.last_name;
    // const phone_number = residentData.phone_number;
    //
    // const sql = `UPDATE Resident set first_name = ?, last_name = ?, phone_number = ? where _id = ?`;
    // return db.promise().execute(sql, [first_name, last_name, phone_number, residentId]);
};

exports.deleteResident = (residentId) => {
    const sql1 = `DELETE FROM ReservedRoom WHERE res_id = ?`;
    const sql2 = `DELETE FROM Resident WHERE _id = ?`;

    return db.promise().execute(sql1, [residentId])
        .then(() => {
            return db.promise().execute(sql2, [residentId]);
        });
};

//MP3
exports.findByPhone = (phone_number) => {
    const sql = `SELECT _id, first_name, last_name, phone_number, password FROM Resident where phone_number = ?`

    return db.promise().execute(sql, [phone_number])
        .then((results, fields) => {
            return results[0];
        });
};

checkPhoneUnique = (phone_number, resId) => {
    let sql, promise;
    if (resId) {
        sql = `SELECT COUNT(1) as c FROM Resident where _id != ? and phone_number = ?`;
        promise = db.promise().query(sql, [resId, phone_number]);
    } else {
        sql = `SELECT COUNT(1) as c FROM Resident where phone_number = ?`;
        promise = db.promise().query(sql, [phone_number]);
    }
    return promise.then((results, fields) => {
        const count = results[0][0].c;
        let err = {};
        if (count > 0) {
            err = {
                details: [{
                    path: ['phone_number'],
                    message: 'The entered phone number is already in use'
                }]
            };
        }
        return err;
    });
}