import type { ChangeEvent } from "react";
import { useState, useCallback, useEffect } from "react";
import { post } from "../server";
import ProgressBar from "@ramonak/react-progress-bar";
import { Row, Col } from "antd";
import TimerBar from "../components/TimerBar";

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

type BanListType = Record<"banScore1" | "banScore2" | "banScore3", boolean>;

const initialBanList = {
  banScore1: false,
  banScore2: false,
  banScore3: false,
};

export default function Tutorial() {
  const [{ score1Submit, score2Submit, score3Submit }, setForm] =
    useState<SubmitType>(initialFormState);

  const [{ banScore1, banScore2, banScore3 }, setBanList] =
    useState<BanListType>(initialBanList);

  const [{ token, score }, setInfo] = useState<UserInfoType>(initialInfoState);

  var totalToken = 0;

  useEffect(() => {
    totalToken = token;
  }, []);

  const calLimit = (key: string, num: string) => {
    var sumSubmit = 0;
    var remainToken = 0;
    console.log("num", num);
    if (key == "score1Submit") {
      sumSubmit = Number(num) + Number(score2Submit) + Number(score3Submit);
      if (sumSubmit > totalToken) {
        num = (
          totalToken -
          (Number(score2Submit) + Number(score3Submit))
        ).toString();
      }
    }
    if (key == "score2Submit") {
      sumSubmit = Number(num) + Number(score1Submit) + Number(score3Submit);
      if (sumSubmit > totalToken) {
        num = (
          totalToken -
          (Number(score1Submit) + Number(score3Submit))
        ).toString();
      }
    }
    if (key == "score3Submit") {
      sumSubmit = Number(num) + Number(score1Submit) + Number(score2Submit);
      if (sumSubmit > totalToken) {
        num = (
          totalToken -
          (Number(score1Submit) + Number(score2Submit))
        ).toString();
      }
    }

    console.log("sum", sumSubmit);

    if (token < Number(num)) {
      num = token.toString();
      remainToken = 0;
    } else {
      remainToken = Number(totalToken) - sumSubmit;
    }

    console.log("rem", remainToken);

    setForm((obj) => ({ ...obj, [key]: num }));
    setInfo((obj) => ({ ...obj, token: remainToken }));
  };

  const changed = useCallback(
    (key: string) => (e: ChangeEvent<HTMLInputElement>) => {
      const { name, value } = e.target;

      var inputNum = value.replace(/[^0-9]/g, "");
      calLimit(key, inputNum);
    },
    [score1Submit, score2Submit, score3Submit]
  );

  //   useEffect(() => {
  //     console.log("Effect");
  //     calLimit(inputName, inputNum);
  //   }, []);

  //   useEffect(() => {
  //     // 설정된 시간 간격마다 setInterval 콜백이 실행된다.
  //     const id = setInterval(() => {
  //       // 타이머 숫자가 하나씩 줄어들도록
  //       setCount((count) => count - 1);
  //     }, 1000);

  //     // 0이 되면 카운트가 멈춤
  //     if (count === 0) {
  //       clearInterval(id);

  //       postTest();
  //     }
  //     return () => clearInterval(id);
  //     // 카운트 변수가 바뀔때마다 useEffecct 실행
  //   }, [count]);

  //   const [data, setData] = useState<object>({});
  //   const [errorMessage, setErrorMessage] = useState<string | null>(null);
  //   const postTest = useCallback(() => {
  //     post("/betting", data)
  //       .then((res) => res.json())
  //       .then((data) => setData(data))
  //       .catch((error) => setErrorMessage(error.message));
  //   }, []);

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
