const ResidentRepository = require('../repository/mysql2/ResidentRepository');
const RoomRepository = require('../repository/mysql2/RoomRepository');
const ReservedRoomRepository = require('../repository/mysql2/ReservedRoomRepository');

exports.showReservedRoomList = (req, res, next) => {
    //res.render('pages/reservedRooms/list', { navLocation: 'reservedRoom' });
    ReservedRoomRepository.getReservedRooms()
        .then(reservedRooms => {
            res.render('pages/reservedRooms/list', {
                reservedRooms: reservedRooms,
                navLocation: 'reservedRoom',
                validationErrors: []
            });
        });
}

exports.showAddReservedRoomForm = (req, res, next) => {
    //res.render('pages/reservedRooms/form', { navLocation: 'reservedRoom' });
    let allRes, allRooms;
    ResidentRepository.getResidents()
        .then(residents => {
            allRes = residents;
            return RoomRepository.getRooms();
        })
        .then(rooms => {
            allRooms = rooms;
            res.render('pages/reservedRooms/form', {
                reservedRoom: {},
                formMode: 'createNew',
                allRes: allRes,
                allRooms: allRooms,
                pageTitle: req.__('resRoom.form.add.pageTitle'),
                btnLabel: req.__('resRoom.form.add.btnLabel'),
                formAction: '/reservedRooms/add',
                navLocation: 'reservedRoom',
                validationErrors: []
            });
        });
}

exports.showEditReservedRoomForm = (req, res, next) => {
    const reservedRoomId = req.params.reservedRoomId;
    let allRes, allRooms;
    ResidentRepository.getResidents()
        .then(residents => {
            allRes = residents;
            return RoomRepository.getRooms();
        })
        .then(rooms => {
            allRooms = rooms;
            return ReservedRoomRepository.getReservedRoomById(reservedRoomId);
        })
        .then(reservedRoom => {
            res.render('pages/reservedRooms/form', {
                reservedRoom: reservedRoom,
                pageTitle: req.__('resRoom.form.edit.pageTitle'),
                formMode: 'showEdit',
                btnLabel: req.__('resRoom.form.edit.btnLabel'),
                formAction: '/reservedRooms/edit',
                navLocation: 'reservedRoom',
                allRes: allRes,
                allRooms: allRooms,
                validationErrors: []
            });
        });
}

exports.showReservedRoomDetails = (req, res, next) => {
    //res.render('pages/reservedRooms/details', { navLocation: 'reservedRoom' });
    const reservedRoomId = req.params.reservedRoomId;
    let allRes, allRooms;
    ResidentRepository.getResidents()
        .then(residents => {
            allRes = residents;
            return RoomRepository.getRooms();
        })
        .then(rooms => {
            allRooms = rooms;
            return ReservedRoomRepository.getReservedRoomById(reservedRoomId);
        })
        .then(reservedRoom => {
            console.log()
            res.render('pages/reservedRooms/form', {
                reservedRoom: reservedRoom,
                pageTitle: req.__('resRoom.form.details.pageTitle'),
                formMode: 'showDetails',
                formAction: '',
                navLocation: 'reservedRoom',
                allRes: allRes,
                allRooms: allRooms,
                validationErrors: []
            });
        });
}

exports.addReservedRoom = (req, res, next) => {
    const resRoomData = {...req.body };
    ReservedRoomRepository.createReservedRoom(resRoomData)
        .then(result => {
            res.redirect('/reservedRooms');
        })
        .catch(err => {
            let allRes, allRooms;
            const resPromise = ResidentRepository.getResidents();
            const roomPromise = RoomRepository.getRooms();
            Promise.all([resPromise, roomPromise])
                .then(([residents, rooms]) => {
                    res.render('pages/reservedRooms/form', {
                        reservedRoom: resRoomData,
                        pageTitle: req.__('resRoom.form.add.pageTitle'),
                        formMode: 'createNew',
                        btnLabel: req.__('resRoom.form.add.btnLabel'),
                        formAction: '/reservedRooms/add',
                        navLocation: 'reservedRoom',
                        allRes: residents,
                        allRooms: rooms,
                        validationErrors: err.details
                    });
                });
        });
};

exports.updateReservedRoom = (req, res, next) => {
    const resRoomId = req.body._id;
    const resRoomData = {...req.body };
    ReservedRoomRepository.updateReservedRoom(resRoomId, resRoomData)
        .then(result => {
            res.redirect('/reservedRooms');
        })
        .catch(err => {
            let allRes, allRooms;
            const resPromise = ResidentRepository.getResidents();
            const roomPromise = RoomRepository.getRooms();
            Promise.all([resPromise, roomPromise])
                .then(([residents, rooms]) => {
                    res.render('pages/reservedRooms/form', {
                        reservedRoom: resRoomData,
                        pageTitle: req.__('resRoom.form.edit.pageTitle'),
                        formMode: 'edit',
                        btnLabel: req.__('resRoom.form.edit.btnLabel'),
                        formAction: '/reservedRooms/edit',
                        navLocation: 'reservedRoom',
                        allRes: residents,
                        allRooms: rooms,
                        validationErrors: err.details
                    });
                });
        });
};

exports.deleteReservedRoom = (req, res, next) => {
    const resRoomId = req.params.reservedRoomId;
    ReservedRoomRepository.deleteManyReservedRoom(resRoomId)
        .then(() => {
            res.redirect('/reservedRooms');
        });
};