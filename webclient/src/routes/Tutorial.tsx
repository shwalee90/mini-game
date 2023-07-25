import type { ChangeEvent } from "react";
import { useState, useCallback, useEffect } from "react";
import { post } from "../server";
import ProgressBar from "@ramonak/react-progress-bar";
import { Row, Col } from "antd";
import TimerBar from "../components/TimerBar";
import Table from "../components/Table";
import "../css/tableStyles.css";

import ScoreBoard from "../components/ScoreBoard";


type SubmitType = Record<
  "score1Submit" | "score2Submit" | "score3Submit" | "thisScore",
  string
>;

type total = {
  totalToken : number
}


const initialFormState = {
  score1Submit: "1",
  score2Submit: "1",
  score3Submit: "1",
  thisScore : '',
};

type errMsgType = Record<
  "ErrMsg",
  string[]
>;

const initialErrMsg = {
  ErrMsg : [] ,

}


export interface UserInfoType {
  token : number,
  score : number,
  banScore1: boolean;
  banScore2: boolean;
  banScore3: boolean;
  zeroCnt: number;
  totalToken : number;
}

const initialInfoState = {
  token: 10,
  score: 0,
  banScore1: false,
  banScore2: false,
  banScore3: false,
  zeroCnt: 0,
  totalToken : 10,
};

// export interface BanListType  {

// };

// const initialBanList = {

// };





export default function Tutorial() {
  const [{ score1Submit, score2Submit, score3Submit ,thisScore }, setForm] =
    useState<SubmitType>(initialFormState);

  const [{ ErrMsg }, setErrmsg] =
    useState<errMsgType>(initialErrMsg);



  const [{ banScore1, banScore2, banScore3, zeroCnt }, setBanList] =
    useState<UserInfoType>(initialInfoState);

  const [{ token, score }, setInfo] = useState<UserInfoType>(initialInfoState);

  useEffect(() => {

    setInfo((obj) => ({ ...obj, token: token-3 }));

  } ,[])

  var totalToken = initialInfoState.token;

  const calRemain = (
    num: string,
    sum: number,
    beforeVal: string ,
    errMsgList : string[]
  ) => {
    let tokenAndErrMsg = new Map();
    var remainToken = 0;

    var diffNum = Number(num) - Number(beforeVal);
    if (token < diffNum && diffNum > 0) {
      errMsgList.push('입력값이 남은 token보다 큽니다.')
      remainToken = 0;
    }
    
    else if (token > diffNum && diffNum > 0) {
      remainToken = token - diffNum;
    }

    else  {
      remainToken = token - diffNum;
    }
    
    tokenAndErrMsg.set('remainToken' , remainToken);
    tokenAndErrMsg.set('errMsgList' , errMsgList )


    return tokenAndErrMsg;
  };

  const calLimit = (key: string, num: string, beforeVal: string) => {
    var sumSubmit = 0;
    var remainToken = 0;
    var errMsgList:string[] = [];
    if (key === "score1Submit") {
      sumSubmit = Number(num) + Number(score2Submit) + Number(score3Submit);
      if (sumSubmit > totalToken) {
        errMsgList.push("입력값의 합이 총 토큰 보다 큽니다.")
        num = (
          totalToken -
          (Number(score2Submit) + Number(score3Submit))
        ).toString();
      }
    }
    if (key === "score2Submit") {
      sumSubmit = Number(num) + Number(score1Submit) + Number(score3Submit);
      if (sumSubmit > totalToken) {
        errMsgList.push("입력값의 합이 총 토큰 보다 큽니다.")
        num = (
          totalToken -
          (Number(score1Submit) + Number(score3Submit))
        ).toString();
      }
    }
    if (key === "score3Submit") {
      sumSubmit = Number(num) + Number(score1Submit) + Number(score2Submit);
      if (sumSubmit > totalToken) {
        errMsgList.push("입력값의 합이 총 토큰 보다 큽니다.")
        num = (
          totalToken -
          (Number(score1Submit) + Number(score2Submit))
        ).toString();
      }
    }

    let tokenAndErrMsg  = calRemain(num, sumSubmit, beforeVal,errMsgList);

    remainToken = tokenAndErrMsg.get('remainToken');
    errMsgList = tokenAndErrMsg.get('errMsgList');


    if (zeroCnt === 2 && Number(num) === 0) {
      errMsgList.push('0을 더 이상 사용할 수 없습니다.')
      setErrmsg((obj) => ({
        ...obj,
        ErrMsg : errMsgList
      }));
      setForm((obj) => ({ ...obj , thisScore: key}));
      return;
    }

    if (Number(beforeVal) === 0 && Number(num) !== 0) {
      if (key === "score1Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt - 1,
          banScore1: false,
        }));
      }
      if (key === "score2Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt - 1,
          banScore2: false,
        }));
      }
      if (key === "score3Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt - 1,
          banScore3: false,
        }));
      }
    }

    if (Number(num) === 0 && Number(beforeVal) !== 0) {
      if (key === "score1Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt + 1,
          banScore1: true,
        }));
      }
      if (key === "score2Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt + 1,
          banScore2: true,
        }));
      }
      if (key === "score3Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt + 1,
          banScore3: true,
        }));
      }
    }

    setErrmsg((obj) => ({
      ...obj,
      ErrMsg : errMsgList
    }));
    setForm((obj) => ({ ...obj, [key]: num , thisScore: key}));
    setInfo((obj) => ({ ...obj, token: remainToken }));
  };

  const handleDownKey = (key:string) => (e: React.KeyboardEvent<HTMLElement>) => {
   var beforeVal = "";
      if (key === "score1Submit") {
        beforeVal = score1Submit;
      }
      if (key === "score2Submit") {
        beforeVal = score2Submit;
      }
      if (key === "score3Submit") {
        beforeVal = score3Submit;
      }
      if(e.key === beforeVal ){
        setErrmsg((obj) => ({
          ...obj,
          ErrMsg : []
        }));
      }
  }

  const changed = useCallback(
    (key: string) => (e: ChangeEvent<HTMLInputElement>) => {
      const { name, value } = e.target;
      var beforeVal = "";
      if (key === "score1Submit") {
        beforeVal = score1Submit;
      }
      if (key === "score2Submit") {
        beforeVal = score2Submit;
      }
      if (key === "score3Submit") {
        beforeVal = score3Submit;
      }

      var inputNum = value.replace(/[^0-9]/g, "");
      if(inputNum !== ""){
        calLimit(key, inputNum, beforeVal);
      }
    },
    [score1Submit, score2Submit, score3Submit , token]
  );

  return (
    <section className="w-full mt-4">
      <div>
        <p className="p-4 text-xl text-center ">Tutorial</p>
      </div>
     
      <ScoreBoard  score={score} zeroCnt= {zeroCnt} banScore1 ={banScore1} banScore2={banScore2} banScore3 ={banScore3} token ={token} totalToken = {totalToken} ></ScoreBoard> 
      
      <TimerBar></TimerBar>

      <div>
        <Row>
          <Col span={12}>
            <Row>
            <Col span= {4}>
            <p>SCORE 1 :</p>
            </Col>
            <Col span= {4}>
            <input
              type="string"
              name="scoreOne"
              className="w-full p-3 mb-4 input primary"
              value={score1Submit}
              onChange={changed("score1Submit")}
              onKeyDown = {handleDownKey("score1Submit")}
            />
            </Col>
            </Row>
            <Row>
            <Col span= {12}>
              {  thisScore === "score1Submit"  && ErrMsg.length !== 0 ?
                ErrMsg.map((errMsg ,index) =>(
                  <p key={index}  >
                    {errMsg}
                  </p>)
                  )
                  :
                  <p></p>
              }
            </Col>
            </Row>
          </Col>
          <Col span={12}>
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <Row>
            <Col span= {4}>
            <p>SCORE 2 :</p>
            </Col>
            <Col span= {4}>
            <input
              type="string"
              name="scoreTwo"
              className="w-full p-3 mb-4 input primary"
              value={score2Submit}
              onChange={changed("score2Submit")}
              onKeyDown = {handleDownKey("score2Submit")}
            />
            </Col>
            </Row>
            <Row>
            <Col span= {12}>
              { thisScore === "score2Submit"  &&  ErrMsg.length !== 0 ?
                ErrMsg.map((errMsg ,index) =>(
                  <p key={index}>
                    {errMsg}
                  </p>)
                  )
                  :
                  <p></p>
              }
            </Col>
            </Row>
          </Col>
          <Col span={12}>
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <Row>
            <Col span= {4}>
            <p>SCORE 3 :</p>
            </Col>
            <Col span= {4}>
            <input
              type="string"
              name="scoreThree"
              className="w-full p-3 mb-4 input primary"
              value={score3Submit}
              onChange={changed("score3Submit")}
              onKeyDown = {handleDownKey("score3Submit")}
            />
            </Col>
            </Row>
            <Row>
            <Col span= {12}>
              { thisScore === "score3Submit"  &&  ErrMsg.length !== 0 ?
                ErrMsg.map((errMsg ,index) =>(
                  <p key={index}>
                    {errMsg}
                  </p>)
                  )
                  :
                  <p></p>
              }
            </Col>
            </Row>
          </Col>
          <Col span={12}>
          </Col>
        </Row>
        
      </div>
    </section>
  );
}
