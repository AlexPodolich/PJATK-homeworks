import { Link } from 'react-router-dom'
function Navigation() {
    return (
        <nav>
            <ul>
                <li><Link to="/">Main page</Link></li>
                <li><Link to="/rooms">Rooms</Link></li>
                <li><Link to="/reservedRooms">Reserved Rooms</Link></li>
                <li><Link to="/residents">Residents</Link></li>
            </ul>
        </nav>
    )
}

export default Navigation