import type { FC } from "react";
import { Row, Col } from "antd";
import Tutorial, { ComSubmitType, SubmitType } from "../routes/Tutorial";

export type RoundResultProps = {
  score: string;
  comSub: ComSubmitType;
  subInfo: SubmitType;
};

const RoundResult: FC<RoundResultProps> = ({ score, subInfo, comSub }) => {
  let resultMsg = "";

  return <Col span={4}>{resultMsg}</Col>;
};

export default RoundResult;
