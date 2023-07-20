import {Routes, Route} from 'react-router-dom'
import NoMatch from './NoMatch'
import Home from './Home'
import Match from './Match'
import Tutorial from './Tutorial'
export default function RoutesSetup(){
    return (
        <Routes>
            <Route path="/" element={<Home />}/>
            <Route path="/welcome" element={<Home title="Welcome to our site" />}/>
            <Route path="/match" element={<Match/>} />
            <Route path="/tutorial" element={<Tutorial/>} />
            <Route path='*' element={<NoMatch />}/>
        </Routes>
    )
}