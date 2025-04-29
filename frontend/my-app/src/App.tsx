import './App.css'
import LayoutWrapper from "@/components/LayoutWrapper.tsx";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import HomePage from "@/pages/Home.tsx";
import PetsPage from "@/pages/Pets.tsx";
import BuyPage from "@/pages/BuyPage.tsx";
import PetSeedPage from "@/pages/PetSeedPage.tsx";
import UserSeedPage from "@/pages/UserSeedPage.tsx";

function App() {

  return (
    <BrowserRouter>
      <LayoutWrapper>
          <Routes>
              <Route path="/" element={<HomePage />} />
              <Route path="/admin" />
              <Route path="/pets" element={<PetsPage withoutOwner={false}/>}/>
              <Route path="/petsWithoutOwner" element={<PetsPage withoutOwner={true}/>}/>
              <Route path="/buy" element={<BuyPage/>} />
              <Route path="/user-seed" element={<UserSeedPage/>} />
              <Route path="/pet-seed" element={<PetSeedPage/>} />
          </Routes>
      </LayoutWrapper>
    </BrowserRouter>
  )
}

export default App
