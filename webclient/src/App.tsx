
import {Provider as ReduxProvider} from 'react-redux'
import { DndProvider } from 'react-dnd'; 
import { HTML5Backend } from 'react-dnd-html5-backend';
import { BrowserRouter } from 'react-router-dom';
import RoutesSetup from './routes/RoutesSetup'
import './App.css';

function App() {
  return (

    <BrowserRouter>
      <RoutesSetup/>
    </BrowserRouter>

    );
}

export default App;
