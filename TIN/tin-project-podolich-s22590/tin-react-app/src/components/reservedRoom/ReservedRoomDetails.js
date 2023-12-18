import React from "react"
import { Link, useParams } from 'react-router-dom'
import { getReservedRoomByIdApiCall } from '../../apiCalls/reservedRoomApiCalls'
import { getFormattedDate } from '../../helpers/dateHelper'

function EmploymentDetails() {
    let { reservedRoomId } = useParams()
    reservedRoomId = parseInt(reservedRoomId)
    const reservedRoom = getReservedRoomByIdApiCall(reservedRoomId)
    const reservedRoomDateFrom = reservedRoom.start_date ? getFormattedDate(reservedRoom.start_date) : ""
    const reservedRoomDateTo = reservedRoom.end_date ? getFormattedDate(reservedRoom.end_date) : ""

    return (
        <main>
            <h2>Reserved room details</h2>
            <p>Room number: {reservedRoom.room.number}</p>
            <p>Room price: {reservedRoom.room.price}</p>
            <p>Resident name: {reservedRoom.resident.first_name} </p>
            <p>Resident surname: {reservedRoom.resident.last_name} </p>
            <p>Start reservation date: {reservedRoomDateFrom} </p>
            {reservedRoomDateTo && <p>End reservation date: {reservedRoomDateTo} </p>}
            <p>
                <Link to="/reservedRooms" className="list-actions-button-details">Back</Link>
            </p>
        </main>
    )
}

export default EmploymentDetails