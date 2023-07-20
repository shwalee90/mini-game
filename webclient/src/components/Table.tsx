import React from "react";
import { useTable } from "react-table";

interface IProps {
  columns: any[];
  data: any[];
}

function Table({ columns, data }: IProps) {
  const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } =
    useTable({ columns, data });

  return (
    <table {...getTableProps()}>
      <thead>
        {headerGroups.map((headerGroup) => (
          <tr {...headerGroup.getHeaderGroupProps()}>
            {headerGroup.headers.map((column) => (
              <th {...column.getHeaderProps()}>{column.render("Header")}</th>
            ))}
          </tr>
        ))}
      </thead>
      <tbody {...getTableBodyProps()}>
        {rows.map((row) => {
          prepareRow(row);
          return (
            <tr {...row.getRowProps()}>
              {row.cells.map((cell) => (
                <td {...cell.getCellProps()}>
                  {cell.render("Cell") === false ? "사용 가능" : "사용 불가능"}
                </td>
              ))}
            </tr>
          );
        })}
      </tbody>
    </table>
  );
}

export default Table;
