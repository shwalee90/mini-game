import type { FC } from "react";
import { Row, Col } from "antd";
import Tutorial, { ComSubmitType, SubmitType } from "../routes/Tutorial";

export type RoundResultProps = {
  resultMsg: string;
};

const RoundResult: FC<RoundResultProps> = ({ resultMsg }) => {
  return <Col span={4}>{resultMsg}</Col>;
};

export default RoundResult;
