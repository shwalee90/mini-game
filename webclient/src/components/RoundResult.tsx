import type { FC } from "react";
import { Row, Col } from "antd";
import Tutorial, { ComSubmitType, SubmitType } from "../routes/Tutorial";

export type RoundResultProps = {
  resultMsg: string;
};

type WinLoseType = (x: string) => boolean;

const RoundResult: FC<RoundResultProps> = ({ resultMsg }) => {
  var str = resultMsg || "";

  const winLose: WinLoseType = (str: string) => {
    if (str == "") {
      return true;
    } else {
      if (str.substring(0, 2) == "+0") {
        return true;
      } else {
        return false;
      }
    }
  };

  return (
    <Row>
      {winLose(str) ? (
        <p>{resultMsg}</p>
      ) : (
        <p className="text-lime-500">{resultMsg}</p>
      )}
    </Row>
  );
};

export default RoundResult;
