import React from 'react'
import { Link, useParams } from 'react-router-dom'
import { getResidentByIdApiCall } from '../../apiCalls/residentApiCalls'
import { getFormattedDate } from '../../helpers/dateHelper'

function ResidentDetails() {
    let { resId } = useParams()
    resId = parseInt(resId)
    const res = getResidentByIdApiCall(resId)

    return (
        <main>
            <h2>Residents details</h2>
            <p>Name: {res.first_name}</p>
            <p>Surname: {res.last_name} </p>
            <p>Phone number: {res.phone_number} </p>
            <h2>Reserved room details</h2>
            <table className="table-list">
                <thead>
                    <tr>
                        <th>Room number</th>
                        <th>Start reservation date</th>
                        <th>End reservation date</th>
                    </tr>
                </thead>
                <tbody>
                    {res.reservedRooms.map(
                        reservedRoom =>
                            <tr key={reservedRoom._id}>
                                <td>{reservedRoom.room.number}</td>
                                <td>{reservedRoom.start_date ? getFormattedDate(reservedRoom.start_date) : ""}</td>
                                <td>{reservedRoom.end_date ? getFormattedDate(reservedRoom.end_date) : ""}</td>
                            </tr>
                    )}
                </tbody>
            </table>
            <p>
                <Link to="/residents" className="list-actions-button-details">Back</Link>
            </p>
        </main>
    )
}
export default ResidentDetails