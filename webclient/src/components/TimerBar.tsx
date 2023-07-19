import type { ChangeEvent } from "react";
import { useState, useCallback, useEffect } from "react";
import { post } from "../server";
import ProgressBar from "@ramonak/react-progress-bar";
import { Row, Col } from "antd";

export default function TimerBar() {
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
  );
}
