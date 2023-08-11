import type { ChangeEvent } from "react";
import { useState, useCallback, useEffect } from "react";
import { post } from "../server";
import ProgressBar from "@ramonak/react-progress-bar";
import { Row, Col } from "antd";
import TimerBar from "../components/TimerBar";
import Table from "../components/Table";
import "../css/tableStyles.css";

import ResultMemo from "../components/ResultMemo";
import ScoreBoard from "../components/ScoreBoard";
import RoundResult from "../components/RoundResult";

export type SubmitType = Record<
  "score1Submit" | "score2Submit" | "score3Submit" | "thisScore" | "resultMsg",
  string
>;

export type ComSubmitType = Record<
  "comScore1Submit" | "comScore2Submit" | "comScore3Submit" | "resultMsg",
  string
>;

const initialFormState = {
  score1Submit: "1",
  score2Submit: "1",
  score3Submit: "1",
  resultMsg: "",
  thisScore: "",
};

const initialComFormState = {
  comScore1Submit: "?",
  comScore2Submit: "?",
  comScore3Submit: "?",
  resultMsg: "",
};

type errMsgType = Record<"ErrMsg", string[]>;

const initialErrMsg = {
  ErrMsg: [],
};

export type UserInfoType = {
  round: number;
  token: number;
  totalScore: number;
  totalToken: number;
  status: string;
};

export type ComInfoType = {
  token: number;
  totalScore: number;
  totalToken: number;
};

export type BanInfoType = {
  // banScore1: boolean;
  // banScore2: boolean;
  // banScore3: boolean;
  banList: boolean[][];
  zeroCnt: number[];
};

const initialInfoState = {
  round: 1,
  token: 10,
  totalScore: 0,
  banList: [
    [false, false, false],
    [false, false, false],
  ],
  zeroCnt: [0, 0],
  totalToken: 10,
  status: "EQUAL",
};

const initialPost = {
  round: 1,
  token: 10,
  totalScore: 0,
  totalToken: 10,
  banList: [
    [false, false, false],
    [false, false, false],
  ],
  zeroCnt: [0, 0],
  score1Submit: "1",
  score2Submit: "1",
  score3Submit: "1",
  status: "EQUAL",
};

export default function Tutorial() {
  const [SubmitInfo, setForm] = useState<SubmitType>(initialFormState);

  const [ComSubmit, setComForm] = useState<ComSubmitType>(initialComFormState);

  const [{ ErrMsg }, setErrmsg] = useState<errMsgType>(initialErrMsg);

  const [BanInfo, setBanList] = useState<BanInfoType>(initialInfoState);

  const [UserInfo, setInfo] = useState<UserInfoType>(initialInfoState);

  const [ComInfo, setComInfo] = useState<ComInfoType>(initialInfoState);

  const [postData, setPostData] = useState<
    UserInfoType | BanInfoType | SubmitType
  >(initialPost);

  useEffect(() => {
    setInfo((obj) => ({ ...obj, token: UserInfo.token - 3 }));
  }, []);

  useEffect(() => {
    setPostData((obj) => ({
      ...obj,
      round: UserInfo.round,
      totalScore: UserInfo.totalScore,
      totalToken: UserInfo.totalToken,
      banList: BanInfo.banList,
      score1Submit: SubmitInfo.score1Submit,
      score2Submit: SubmitInfo.score2Submit,
      score3Submit: SubmitInfo.score3Submit,
    }));
  }, [UserInfo, BanInfo, SubmitInfo]);

  const postTest = useCallback(() => {
    post("/betting/tutorial", postData)
      .then((res) => res.json())
      .then((postData) => {
        console.log("apiResult : ", postData);

        setForm((obj) => ({
          ...obj,
          score1Submit: postData[0].score1Submit,
          score2Submit: postData[0].score2Submit,
          score3Submit: postData[0].score3Submit,
          resultMsg: postData[0].resultMsg,
        }));

        setComForm((obj) => ({
          ...obj,
          comScore1Submit: postData[1].score1Submit,
          comScore2Submit: postData[1].score2Submit,
          comScore3Submit: postData[1].score3Submit,
          resultMsg: postData[0].resultMsg,
        }));

        let timer = setTimeout(() => {
          setInfo((obj) => ({
            ...obj,
            round: postData[0].round,
            status: postData[0].status,
            totalScore: postData[0].totalScore,
            totalToken: postData[0].totalToken,
            token: postData[0].totalToken - 3,
          }));

          setComInfo((obj) => ({
            ...obj,
            round: postData[1].round,
            status: postData[1].status,
            totalScore: postData[1].totalScore,
            totalToken: postData[1].totalToken,
          }));

          setForm((obj) => ({
            ...obj,
            score1Submit: "1",
            score2Submit: "1",
            score3Submit: "1",
            resultMsg: "",
          }));

          setComForm((obj) => ({
            ...obj,
            comScore1Submit: "?",
            comScore2Submit: "?",
            comScore3Submit: "?",
            resultMsg: "",
          }));
        }, 3000);
      })
      .catch((error) => console.log(error));
  }, [postData]);

  const calRemain = (
    num: string,
    sum: number,
    beforeVal: string,
    errMsgList: string[]
  ) => {
    let tokenAndErrMsg = new Map();
    var remainToken = 0;

    var diffNum = Number(num) - Number(beforeVal);
    if (UserInfo.token < diffNum && diffNum > 0) {
      errMsgList.push("입력값이 남은 token보다 큽니다.");
      remainToken = 0;
    } else if (UserInfo.token > diffNum && diffNum > 0) {
      remainToken = UserInfo.token - diffNum;
    } else {
      remainToken = UserInfo.token - diffNum;
    }

    tokenAndErrMsg.set("remainToken", remainToken);
    tokenAndErrMsg.set("errMsgList", errMsgList);

    return tokenAndErrMsg;
  };

  const calLimit = (key: string, num: string, beforeVal: string) => {
    var sumSubmit = 0;
    var remainToken = 0;
    var errMsgList: string[] = [];
    if (key === "score1Submit") {
      sumSubmit =
        Number(num) +
        Number(SubmitInfo.score2Submit) +
        Number(SubmitInfo.score3Submit);
      if (sumSubmit > UserInfo.totalToken) {
        errMsgList.push("입력값의 합이 총 토큰 보다 큽니다.");
        num = (
          UserInfo.totalToken -
          (Number(SubmitInfo.score2Submit) + Number(SubmitInfo.score3Submit))
        ).toString();
      }
    }
    if (key === "score2Submit") {
      sumSubmit =
        Number(num) +
        Number(SubmitInfo.score1Submit) +
        Number(SubmitInfo.score3Submit);
      if (sumSubmit > UserInfo.totalToken) {
        errMsgList.push("입력값의 합이 총 토큰 보다 큽니다.");
        num = (
          UserInfo.totalToken -
          (Number(SubmitInfo.score1Submit) + Number(SubmitInfo.score3Submit))
        ).toString();
      }
    }
    if (key === "score3Submit") {
      sumSubmit =
        Number(num) +
        Number(SubmitInfo.score1Submit) +
        Number(SubmitInfo.score2Submit);
      if (sumSubmit > UserInfo.totalToken) {
        errMsgList.push("입력값의 합이 총 토큰 보다 큽니다.");
        num = (
          UserInfo.totalToken -
          (Number(SubmitInfo.score1Submit) + Number(SubmitInfo.score2Submit))
        ).toString();
      }
    }

    let tokenAndErrMsg = calRemain(num, sumSubmit, beforeVal, errMsgList);

    remainToken = tokenAndErrMsg.get("remainToken");
    errMsgList = tokenAndErrMsg.get("errMsgList");

    if (BanInfo.zeroCnt[0] === 2 && Number(num) === 0) {
      errMsgList.push("0을 더 이상 사용할 수 없습니다.");
      setErrmsg((obj) => ({
        ...obj,
        ErrMsg: errMsgList,
      }));
      setForm((obj) => ({ ...obj, thisScore: key }));
      return;
    }

    if (Number(beforeVal) === 0 && Number(num) !== 0) {
      let copyBanList = BanInfo.banList;
      if (key === "score1Submit") {
        copyBanList[0][0] = false;

        setBanList((obj) => ({
          ...obj,
          zeroCnt: [BanInfo.zeroCnt[0] - 1, BanInfo.zeroCnt[1]],
          banList: copyBanList,
        }));
      }
      if (key === "score2Submit") {
        copyBanList[0][1] = false;
        setBanList((obj) => ({
          ...obj,
          zeroCnt: [BanInfo.zeroCnt[0] - 1, BanInfo.zeroCnt[1]],
          banList: copyBanList,
        }));
      }
      if (key === "score3Submit") {
        copyBanList[0][2] = false;
        setBanList((obj) => ({
          ...obj,
          zeroCnt: [BanInfo.zeroCnt[0] - 1, BanInfo.zeroCnt[1]],
          banList: copyBanList,
        }));
      }
    }

    if (Number(num) === 0 && Number(beforeVal) !== 0) {
      let copyBanList = BanInfo.banList;
      if (key === "score1Submit") {
        copyBanList[0][0] = true;
        setBanList((obj) => ({
          ...obj,
          zeroCnt: [BanInfo.zeroCnt[0] + 1, BanInfo.zeroCnt[1] + 1],
          banList: copyBanList,
        }));
      }
      if (key === "score2Submit") {
        copyBanList[0][1] = true;
        setBanList((obj) => ({
          ...obj,
          zeroCnt: [BanInfo.zeroCnt[0] + 1, BanInfo.zeroCnt[1] + 1],
          banList: copyBanList,
        }));
      }
      if (key === "score3Submit") {
        copyBanList[0][2] = true;
        setBanList((obj) => ({
          ...obj,
          zeroCnt: [BanInfo.zeroCnt[0] + 1, BanInfo.zeroCnt[1] + 1],
          banList: copyBanList,
        }));
      }
    }

    setErrmsg((obj) => ({
      ...obj,
      ErrMsg: errMsgList,
    }));
    setForm((obj) => ({ ...obj, [key]: num, thisScore: key }));
    setInfo((obj) => ({ ...obj, token: remainToken }));
  };

  const handleDownKey =
    (key: string) => (e: React.KeyboardEvent<HTMLElement>) => {
      var beforeVal = "";
      if (key === "score1Submit") {
        beforeVal = SubmitInfo.score1Submit;
      }
      if (key === "score2Submit") {
        beforeVal = SubmitInfo.score2Submit;
      }
      if (key === "score3Submit") {
        beforeVal = SubmitInfo.score3Submit;
      }
      if (e.key === beforeVal) {
        setErrmsg((obj) => ({
          ...obj,
          ErrMsg: [],
        }));
      }
    };

  const changed = useCallback(
    (key: string) => (e: ChangeEvent<HTMLInputElement>) => {
      const { name, value } = e.target;
      var beforeVal = "";
      if (key === "score1Submit") {
        beforeVal = SubmitInfo.score1Submit;
      }
      if (key === "score2Submit") {
        beforeVal = SubmitInfo.score2Submit;
      }
      if (key === "score3Submit") {
        beforeVal = SubmitInfo.score3Submit;
      }

      var inputNum = value.replace(/[^0-9]/g, "");
      if (inputNum !== "") {
        calLimit(key, inputNum, beforeVal);
      }
    },
    [
      SubmitInfo.score1Submit,
      SubmitInfo.score2Submit,
      SubmitInfo.score3Submit,
      UserInfo.token,
    ]
  );

  return (
    <section className="w-full mt-4">
      <div>
        <p className="p-4 text-xl text-center ">Tutorial</p>
      </div>

      <ScoreBoard
        UserInfo={UserInfo}
        BanInfo={BanInfo}
        Submit={SubmitInfo}
        ComInfo={ComInfo}
        postTest={postTest}
      ></ScoreBoard>

      <div>
        <Row>
          <Col span={12}>
            <Row>
              <Col span={4}>
                <p>SCORE 1 :</p>
              </Col>
              <Col span={4}>
                <input
                  type="string"
                  name="scoreOne"
                  className="w-full p-3 mb-4 input primary"
                  value={SubmitInfo.score1Submit}
                  onChange={changed("score1Submit")}
                  onKeyDown={handleDownKey("score1Submit")}
                />
              </Col>
              <RoundResult resultMsg={SubmitInfo.resultMsg[0]}></RoundResult>
            </Row>
            <Row>
              <Col span={12}>
                {SubmitInfo.thisScore === "score1Submit" &&
                ErrMsg.length !== 0 ? (
                  ErrMsg.map((errMsg, index) => <p key={index}>{errMsg}</p>)
                ) : (
                  <p></p>
                )}
              </Col>
            </Row>
          </Col>
          <Col span={12}>
            <Col span={4}>
              <p>SCORE 1 :</p>
            </Col>
            <Col span={4}>
              <p>{ComSubmit.comScore1Submit}</p>
            </Col>
            <RoundResult resultMsg={ComSubmit.resultMsg[0]}></RoundResult>
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <Row>
              <Col span={4}>
                <p>SCORE 2 :</p>
              </Col>
              <Col span={4}>
                <input
                  type="string"
                  name="scoreTwo"
                  className="w-full p-3 mb-4 input primary"
                  value={SubmitInfo.score2Submit}
                  onChange={changed("score2Submit")}
                  onKeyDown={handleDownKey("score2Submit")}
                />
              </Col>
              <RoundResult resultMsg={SubmitInfo.resultMsg[1]}></RoundResult>
            </Row>
            <Row>
              <Col span={12}>
                {SubmitInfo.thisScore === "score2Submit" &&
                ErrMsg.length !== 0 ? (
                  ErrMsg.map((errMsg, index) => <p key={index}>{errMsg}</p>)
                ) : (
                  <p></p>
                )}
              </Col>
            </Row>
          </Col>
          <Col span={12}>
            <Col span={4}>
              <p>SCORE 2 :</p>
            </Col>
            <Col span={4}>
              <p>{ComSubmit.comScore2Submit}</p>
            </Col>
            <RoundResult resultMsg={ComSubmit.resultMsg[1]}></RoundResult>
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <Row>
              <Col span={4}>
                <p>SCORE 3 :</p>
              </Col>
              <Col span={4}>
                <input
                  type="string"
                  name="scoreThree"
                  className="w-full p-3 mb-4 input primary"
                  value={SubmitInfo.score3Submit}
                  onChange={changed("score3Submit")}
                  onKeyDown={handleDownKey("score3Submit")}
                />
              </Col>
              <RoundResult resultMsg={SubmitInfo.resultMsg[2]}></RoundResult>
            </Row>
            <Row>
              <Col span={12}>
                {SubmitInfo.thisScore === "score3Submit" &&
                ErrMsg.length !== 0 ? (
                  ErrMsg.map((errMsg, index) => <p key={index}>{errMsg}</p>)
                ) : (
                  <p></p>
                )}
              </Col>
            </Row>
          </Col>
          <Col span={12}>
            <Col span={4}>
              <p>SCORE 3 :</p>
            </Col>
            <Col span={4}>
              <p>{ComSubmit.comScore3Submit}</p>
            </Col>
            <RoundResult resultMsg={ComSubmit.resultMsg[2]}></RoundResult>
          </Col>
        </Row>
      </div>

      {/* <ResultMemo></ResultMemo> */}
    </section>
  );
}
