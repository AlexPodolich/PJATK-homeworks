import { Link } from 'react-router-dom'
import { getResidentsApiCall } from '../../apiCalls/residentApiCalls'
import { getRoomsApiCall } from '../../apiCalls/roomApiCalls'

function ReservedRoomForm() {
    const allResidents = getResidentsApiCall()
    const allRooms = getRoomsApiCall()

    return (
        <main>
            <h2>New reserved room</h2>
            <form className="form">
                <label htmlFor="room">Room: <span class="symbol-required">*</span></label>
                <select id="room" name="roomId" required>
                    <option value="">--- Choose room ---</option>
                    {allRooms.map(room =>
                        (<option key={room._id} value={room._id} label={room.number + " (" + room.price + ' zl)'}></option>)
                    )}
                </select>
                <span id="errorRoom" className="errors-text"></span>
                <label htmlFor="resident">Resident: <span class="symbol-required">*</span></label>
                <select id="resident" name="resId" required>
                    <option value="">--- Choose resident ---</option>
                    {allResidents.map(res =>
                        (<option key={res._id} value={res._id} label={res.first_name + " " + res.last_name}></option>)
                    )}
                </select>
                <span id="errorResident" className="errors-text"></span>
                <label htmlFor="start_date">Start Date:</label>
                <input type="date" name="start_date" value="" id="start_date" />
                <span id="errorStartDate" className="errors-text"></span>
                <label htmlFor="end_date">End Date:</label>
                <input type="date" name="end_date" value="" id="end_date" />
                <span id="errorEndDate" className="errors-text"></span>
                <div className="form-buttons">
                    <p id="errorsSummary" className="errors-text"></p>
                    <input className="form-button-submit" type="submit" value="Add" />
                    <Link to="/reservedRooms" className="form-button-cancel">Cancel</Link>
                </div>
            </form>
        </main>
    )
}

export default ReservedRoomForm