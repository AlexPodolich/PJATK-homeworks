import { reservedRoomList, reservedRoomDetailsList } from './reservedRoomApiMockData'

export function getReservedRoomsApiCall() {
    return reservedRoomList;
}

export function getReservedRoomByIdApiCall(reservedRoomId) {
    const resRoom = reservedRoomDetailsList.find(reservedRoom => reservedRoom._id === reservedRoomId)
    return resRoom;
}