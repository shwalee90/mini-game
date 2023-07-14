import type {FC} from 'react'
import {Link} from 'react-router-dom'


type HomeProps = {
    title? : string
}

const Home : FC<HomeProps> = ({title}) =>{
    return (
        <div>
            <div className='flex bg-gray-200 p-4'>
                <Link to ="/">Home</Link>
                <Link to ="/match">매치</Link>
                <Link to ="/tutorial">연습모드</Link>
            </div>
            <p className='text-bold text-center text-xl'>{title ?? 'Home'}</p>
            <div className='mt-4 flex justify-evenly'>
                <Link to ="/match">
                    <button className='btn btn-primary'>매치</button>
                </Link>
            </div>
            <div className='mt-4 flex justify-evenly'>
                <Link to ="/tutorial">
                    <button className='btn btn-primary'>연습모드</button>
                </Link>
            </div>
        </div>

    )
}

export default Home