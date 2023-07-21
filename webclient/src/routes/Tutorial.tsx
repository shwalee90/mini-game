import type { ChangeEvent } from "react";
import { useState, useCallback, useEffect } from "react";
import { post } from "../server";
import ProgressBar from "@ramonak/react-progress-bar";
import { Row, Col } from "antd";
import TimerBar from "../components/TimerBar";
import Table from "../components/Table";
import "../css/tableStyles.css";
import { Button, Icon } from "../theme/daisyui";

type SubmitType = Record<
  "score1Submit" | "score2Submit" | "score3Submit",
  string
>;

const initialFormState = {
  score1Submit: "1",
  score2Submit: "1",
  score3Submit: "1",
};

type UserInfoType = Record<"token" | "score", number>;

const initialInfoState = {
  token: 10,
  score: 0,
};

type BanListType = {
  banScore1: boolean;
  banScore2: boolean;
  banScore3: boolean;
  zeroCnt: number;
};

const initialBanList = {
  banScore1: false,
  banScore2: false,
  banScore3: false,
  zeroCnt: 0,
};

export default function Tutorial() {
  const [{ score1Submit, score2Submit, score3Submit }, setForm] =
    useState<SubmitType>(initialFormState);

  const [{ banScore1, banScore2, banScore3, zeroCnt }, setBanList] =
    useState<BanListType>(initialBanList);

  const [{ token, score }, setInfo] = useState<UserInfoType>(initialInfoState);

  const banColumn = [
    {
      accessor: "Score1",
      Header: "Score1",
    },
    { accessor: "Score2", Header: "Score2" },
    { accessor: "Score3", Header: "Score3" },
  ];
  const banData = [{ Score1: banScore1, Score2: banScore2, Score3: banScore3 }];

  var totalToken = initialInfoState.token;
  const calRemain = (
    num: string,
    sum: number,
    key: string,
    beforeVal: string
  ) => {
    var remainToken = 0;
    if (token < Number(num)) {
      num = token.toString() + Number(beforeVal);
      remainToken = 0;
    } else {
      remainToken = Number(totalToken) - sum;
    }

    return remainToken;
  };

  const calLimit = (key: string, num: string, beforeVal: string) => {
    var sumSubmit = 0;
    var remainToken = 0;
    console.log("num", num);
    if (key === "score1Submit") {
      sumSubmit = Number(num) + Number(score2Submit) + Number(score3Submit);
      if (sumSubmit > totalToken) {
        num = (
          totalToken -
          (Number(score2Submit) + Number(score3Submit))
        ).toString();
      }
    }
    if (key === "score2Submit") {
      sumSubmit = Number(num) + Number(score1Submit) + Number(score3Submit);
      if (sumSubmit > totalToken) {
        num = (
          totalToken -
          (Number(score1Submit) + Number(score3Submit))
        ).toString();
      }
    }
    if (key === "score3Submit") {
      sumSubmit = Number(num) + Number(score1Submit) + Number(score2Submit);
      if (sumSubmit > totalToken) {
        num = (
          totalToken -
          (Number(score1Submit) + Number(score2Submit))
        ).toString();
      }
    }

    console.log("sum", sumSubmit);
    remainToken = calRemain(num, sumSubmit, key, beforeVal);
    console.log("rem", remainToken);

    if (zeroCnt === 2 && Number(num) === 0) {
      return;
    }

    if (Number(beforeVal) === 0 && Number(num) !== 0) {
      if (key == "score1Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt - 1,
          banScore1: false,
        }));
      }
      if (key == "score2Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt - 1,
          banScore2: false,
        }));
      }
      if (key == "score3Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt - 1,
          banScore3: false,
        }));
      }
    }

    if (Number(num) === 0) {
      if (key == "score1Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt + 1,
          banScore1: true,
        }));
      }
      if (key == "score2Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt + 1,
          banScore2: true,
        }));
      }
      if (key == "score3Submit") {
        setBanList((obj) => ({
          ...obj,
          zeroCnt: zeroCnt + 1,
          banScore3: true,
        }));
      }
    }

    setForm((obj) => ({ ...obj, [key]: num }));
    setInfo((obj) => ({ ...obj, token: remainToken }));
  };

  const changed = useCallback(
    (key: string) => (e: ChangeEvent<HTMLInputElement>) => {
      const { name, value } = e.target;
      var beforeVal = "";
      if (key == "score1Submit") {
        beforeVal = score1Submit;
      }
      if (key == "score2Submit") {
        beforeVal = score2Submit;
      }
      if (key == "score3Submit") {
        beforeVal = score3Submit;
      }

      var inputNum = value.replace(/[^0-9]/g, "");
      calLimit(key, inputNum, beforeVal);
    },
    [score1Submit, score2Submit, score3Submit]
  );

  return (
    <section className="w-full mt-4">
      <div>
        <p className="p-4 text-xl text-center ">Tutorial</p>
      </div>
      <Row>
        <Col span={12}>
          <p> 점수 : {JSON.stringify("")}</p>
          <p> 총 토큰 : {totalToken}</p>
          <p> 남은 토큰 : {token}</p>
          <p> 0 사용 횟수 : {zeroCnt} / 2</p>
          <Row>
            <p>SCORE1 :</p>{" "}
            {banScore1 === false && zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
          <Row>
            <p>SCORE2 : </p>{" "}
            {banScore2 === false && zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
          <Row>
            <p>SCORE3 : </p>{" "}
            {banScore3 === false && zeroCnt !== 2 ? (
              <Button className="btn btn-success"> 사용 가능</Button>
            ) : (
              <Button className="btn btn-error"> 사용 불가능</Button>
            )}
          </Row>
        </Col>
        <Col span={12}>
          <p> 점수 : {JSON.stringify("")}</p>
          <p> 총 토큰 : {totalToken}</p>
          <p> 남은 토큰 : {token}</p>
        </Col>
      </Row>

      <TimerBar></TimerBar>

      <div>
        <Row>
          <Col span={12}>
            <p>SCORE 1 :</p>
            <input
              type="string"
              name="scoreOne"
              className="w-full p-3 mb-4 input primary"
              value={score1Submit}
              onChange={changed("score1Submit")}
            />
          </Col>
          <Col span={12}></Col>
        </Row>
        <Row>
          <Col span={12}>
            <p>SCORE 2 :</p>
            <input
              type="string"
              name="scoreTwo"
              className="w-full p-3 mb-4 input primary"
              value={score2Submit}
              onChange={changed("score2Submit")}
            />
          </Col>
          <Col span={12}>
            <p>{score2Submit}</p>
          </Col>
        </Row>
        <Row>
          <Col span={12}>
            <p>SCORE 3 :</p>
            <input
              type="string"
              name="scoreThree"
              className="w-full p-3 mb-4 input primary"
              value={score3Submit}
              onChange={changed("score3Submit")}
            />
          </Col>
          <Col span={12}></Col>
        </Row>
      </div>
    </section>
  );
}
