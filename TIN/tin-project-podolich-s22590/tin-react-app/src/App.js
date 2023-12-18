import Header from './components/fragments/Header';
import Navigation from './components/fragments/Navigation';
import MainContent from './components/other/MainContent';
import Footer from './components/fragments/Footer';
import ResidentList from './components/resident/ResidentList';
import ResidentDetails from './components/resident/ResidentDetails';
import ResidentForm from './components/resident/ResidentForm';
import RoomList from './components/room/RoomList';
import RoomDetails from './components/room/RoomDetails';
import RoomForm from './components/room/RoomForm';
import ReservedRoomList from './components/reservedRoom/ReservedRoomList';
import ReservedRoomDetails from './components/reservedRoom/ReservedRoomDetails';
import ReservedRoomForm from './components/reservedRoom/ReservedRoomForm';

import {Routes, Route } from 'react-router-dom';

function App() {
  return (
    <>
      <Header />
      <Navigation />
      <Routes>
        <Route path="/" element={<MainContent />} />
        <Route path="rooms">
            <Route index={true} element={<RoomList />} />
            <Route path="details/:roomId" element={<RoomDetails />} />
            <Route path="add" element={<RoomForm />} />
            <Route path="edit/:roomId" element={<RoomForm />} />
        </Route>
        <Route path="reservedRooms">
            <Route index={true} element={<ReservedRoomList />} />
            <Route path="details/:reservedRoomId" element={<ReservedRoomDetails />} />
            <Route path="add" element={<ReservedRoomForm />} />
            <Route path="edit/:reservedRoomId" element={<ReservedRoomForm />} />
        </Route>
        <Route path="residents">
            <Route index={true} element={<ResidentList />} />
            <Route path="details/:resId" element={<ResidentDetails />} />
            <Route path="add" element={<ResidentForm />} />
            <Route path="edit/:resId" element={<ResidentForm />} />
        </Route>
      </Routes>
      <Footer />
    </>
  );
}

export default App;