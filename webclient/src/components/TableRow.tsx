// import styled, { css } from "styled-components";

// const Tags = styled.span`
//   background-color: #00d82c;
//   color: #fff;
//   font-size: 1.2rem;
//   margin: 0 0.5rem;
//   padding: 1rem 2rem;
//   border-radius: 3rem;
// `;

// const Balance = ({ values }) => {
//   return (
//     <div style={{ textAlign: "center" }}>
//       {values.map((coin, idx) => {
//         return <Tags key={idx}>{coin}</Tags>;
//       })}
//     </div>
//   );
// };
import { useCallback } from "react"
import { useNavigate} from 'react-router-dom'

export default function TableRow(){
    const navigate = useNavigate()
    const goBack = useCallback(() => {
        navigate(-1)
    } ,[navigate])

    return (
        <div className="flex flex-col p-4">
            <p className="text-xl text-center alert alert-error">no page found</p>
            <div className="flex justify-center mt-4">
                <button className="ml-4 btn btn-primary btn-xs" onClick={goBack}>
                    go back
                </button>
            </div>
        </div>

    )

}