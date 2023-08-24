import type { FC } from "react";

export type HorizonLineType = {
  text: string;
};

const HorizonLine: FC<HorizonLineType> = ({ text }) => {
  return (
    <div
      style={{
        width: "100%",
        textAlign: "center",
        borderBottom: "1px solid #aaa",
        lineHeight: "0.1em",
        margin: "10px 0 20px",
      }}
    >
      <span style={{ background: "#fff" }}></span>
    </div>
  );
};

export default HorizonLine;
