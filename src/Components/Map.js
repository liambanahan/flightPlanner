import "./Map.css";

import {MapContainer, TileLayer, Marker } from "react-leaflet"
import "leaflet/dist/leaflet.css"
import {Icon} from "leaflet";



export default function Map() {
  const airportIcon = new Icon({
    iconUrl: require ("./Pictures/Airport Logo.png"),
    iconSize: [50,50]
  })
  const airportMarkers = [
    {
      //Austin-Bergstorm International
      cord: [30.1975,-97.664],
      text: "AUS"

    }
  ]
    
  return (
    <div className="Map"> 
    <p>Flight Tracker</p>
    
    <MapContainer center={[39.8282, -98.57]} zoom={3.5}>
      <TileLayer
      url="https://tile.openstreetmap.org/{z}/{x}/{y}.png"
      />
      {airportMarkers.map(marker =>(<Marker position ={marker.cord} icon = {airportIcon}></Marker>
      ))}

      </MapContainer>
    </div>
    
    

  );
}

