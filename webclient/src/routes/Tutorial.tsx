import type { ChangeEvent } from "react";
import { useState, useCallback, useEffect } from "react";
import { post } from "../server";
import ProgressBar from "@ramonak/react-progress-bar";
import { Row, Col } from "antd";

type SubmitType = Record<"lev1Submit" | "lev2Submit" | "lev3Submit", number>;
const initialFormState = {
  lev1Submit: 1,
  lev2Submit: 1,
  lev3Submit: 1,
};

export default function Tutorial() {
  const [{ lev1Submit, lev2Submit, lev3Submit }, setForm] =
    useState<SubmitType>(initialFormState);

  const changed = useCallback(
    (key: string) => (e: ChangeEvent<HTMLInputElement>) => {
      const { value } = e.target;
      var onlynum = value.replace(/[^0-9]/g, "");
      if (parseInt(onlynum) > 10) {
        onlynum = "10";
      }
      setForm((obj) => ({ ...obj, [key]: onlynum }));
    },
    []
  );
  const [count, setCount] = useState(10);

  useEffect(() => {
    // 설정된 시간 간격마다 setInterval 콜백이 실행된다.
    const id = setInterval(() => {
      // 타이머 숫자가 하나씩 줄어들도록
      setCount((count) => count - 1);
    }, 1000);

    // 0이 되면 카운트가 멈춤
    if (count === 0) {
      clearInterval(id);

      postTest();
    }
    return () => clearInterval(id);
    // 카운트 변수가 바뀔때마다 useEffecct 실행
  }, [count]);

  const [data, setData] = useState<object>({});
  const [errorMessage, setErrorMessage] = useState<string | null>(null);
  const postTest = useCallback(() => {
    post("/betting", data)
      .then((res) => res.json())
      .then((data) => setData(data))
      .catch((error) => setErrorMessage(error.message));
  }, []);

  return (
    <section className="w-full mt-4">
      <div>
        <p className="p-4 text-xl text-center ">Tutorial</p>
      </div>
      <Row>
        <Col span={12}>
          <p> score : {JSON.stringify(data)}</p>
          <p> token : {JSON.stringify(data)}</p>
        </Col>
        <Col span={12}>
          <p> score : {JSON.stringify(data)}</p>
          <p> token : {JSON.stringify(data)}</p>
        </Col>
      </Row>

      <div>
        <ProgressBar
          bgColor="blue"
          width="100%"
          height="40px"
          completed={String(count * 10)}
          customLabel={String(count)}
        ></ProgressBar>
        <span>{count}</span>
      </div>

      <div>
        <Row>
          <Col span={12}>
            <p>SCORE 1 :</p>
            <input
              type="string"
              className="w-full p-3 mb-4 input primary"
              value={lev1Submit}
              onChange={changed("lev1Submit")}
            />
          </Col>
          <Col span={12}></Col>
        </Row>
        <Row>
          <Col span={12}>
            <p>SCORE 2 :</p>
            <input
              type="string"
              className="w-full p-3 mb-4 input primary"
              value={lev2Submit}
              onChange={changed("lev2Submit")}
            />
          </Col>
          <Col span={12}></Col>
        </Row>
        <Row>
          <Col span={12}>
            <p>SCORE 3 :</p>
            <input
              type="string"
              className="w-full p-3 mb-4 input primary"
              value={lev3Submit}
              onChange={changed("lev3Submit")}
            />
          </Col>
          <Col span={12}></Col>
        </Row>
      </div>
    </section>
  );
}
