package DrawingBoard;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

class DrawingB extends JFrame implements ActionListener {
	private JPanel 			pnMenu, pnBoard, pnFigure, pnPen, pnColor;
	private JButton 			btnFigureSquare, btnFigureCircle, btnFigureLine, btnReset, btnPen, btnText;
	private JButton			btnColorRed, btnColorOrange, btnColorYellow, btnColorGreen, btnColorSky, btnColorBlue, btnColorPink, btnColorPurple, btnColorBlack;
	private JButton			btnCurrentClicked;
	
	private Color				colorCurrentClicked;
	private Color				colorOriginalBtn;
	
	private JMenuBar			sysMenuBar;
	private JMenu				sysMenu;
	private JMenuItem			item1_New, item2_OpenFile, item3_Save, item4_Exit;
	
	private JFileChooser		fileChooser;
	
	private Vector<Shape>		shapeList;
	private Vector<Point>		lineList;
	private Vector				allLineList;
	
	private ObjSerialToFile1	objToFile;
	
	private FigureBtnClickEvent 	figureBtnClickEventListener;
	private ColorBtnClickEvent	colorBtnClickEventListener;
	
	DrawingB () {
		// 1.1 사용 패널 정리
		pnMenu 		= new JPanel();
		pnBoard 	= new pnDrawingBoard();
		pnFigure	= new JPanel();
		pnPen		= new JPanel();
		pnColor		= new JPanel();
		
		this.setLayout(new BorderLayout());
		
		this.add(pnMenu, BorderLayout.PAGE_START);
		this.add(pnBoard, BorderLayout.CENTER);
		
		pnBoard.setBackground(Color.WHITE);
		
		// 1.2 사용 벡터 정리
		shapeList	= new Vector<Shape>();
		lineList	= new Vector<Point>();
		
		// 2. 시스템 메뉴
		// 2.1 메뉴바, 메뉴 생성
		sysMenuBar 	= new JMenuBar();
		sysMenu		= new JMenu("FILE");
		
		// 2.1 시스템 메뉴 바, 메뉴 추가
		setJMenuBar(sysMenuBar);
		sysMenuBar.add(sysMenu);
		
		// 2.3 메뉴 아이템 생성
		item1_New 		= new JMenuItem("NEW");
		item2_OpenFile 	= new JMenuItem("OPEN FILE");
		item3_Save		= new JMenuItem("SAVE");
		item4_Exit		= new JMenuItem("EXIT");
		
		// 2.4 메뉴 아이템, 구분선 추가
		sysMenu.add(item1_New);
		sysMenu.addSeparator();
		sysMenu.add(item2_OpenFile);
		sysMenu.addSeparator();
		sysMenu.add(item3_Save);
		sysMenu.addSeparator();
		sysMenu.add(item4_Exit);
		
		// 2.5 메뉴 아이템 이벤트 추가
		item1_New.addActionListener(this);
		item2_OpenFile.addActionListener(this);
		item3_Save.addActionListener(this);
		item4_Exit.addActionListener(this);
		
		// 3. 그림판 메뉴
		pnMenu.setLayout(new FlowLayout());
		pnMenu.add(pnFigure);
		pnMenu.add(pnPen);
		pnMenu.add(pnColor);
		
		// 4. 도형 버튼 		
		btnFigureLine 	= new JButton("LINE");
		btnFigureSquare = new JButton("SQUARE");
		btnFigureCircle = new JButton("CIRCLE");
		btnReset		= new JButton("RESET");
		
		pnFigure.setLayout(new GridLayout(2, 0));
		
		pnFigure.add(btnFigureLine);
		pnFigure.add(btnFigureSquare);
		pnFigure.add(btnFigureCircle);
		pnFigure.add(btnReset);
		
		// 4.1 도형버튼 색상 변경 이벤트 추가
		figureBtnClickEventListener = new FigureBtnClickEvent();
		
		btnFigureLine.addMouseListener(figureBtnClickEventListener);
		btnFigureSquare.addMouseListener(figureBtnClickEventListener);
		btnFigureCircle.addMouseListener(figureBtnClickEventListener);
		btnReset.addMouseListener(figureBtnClickEventListener);
		
		// 5. 펜 버튼
		btnPen = new JButton("PEN");
		btnText = new JButton("TEXT");
		
		pnPen.setLayout(new GridLayout(2, 0));
		
		pnPen.add(btnPen);
		pnPen.add(btnText);
		
		btnPen.addMouseListener(figureBtnClickEventListener);
		btnText.addMouseListener(figureBtnClickEventListener);
		
		// 6. 색상 버튼
		btnColorRed 	= new JButton(new ImageIcon("img/red.png"));
		btnColorOrange 	= new JButton(new ImageIcon("img/orange.png"));
		btnColorYellow 	= new JButton(new ImageIcon("img/yellow.png"));
		btnColorGreen 	= new JButton(new ImageIcon("img/green.png"));
		btnColorSky 	= new JButton(new ImageIcon("img/sky.png"));
		btnColorBlue 	= new JButton(new ImageIcon("img/blue.png"));
		btnColorPink 	= new JButton(new ImageIcon("img/pink.png"));
		btnColorPurple 	= new JButton(new ImageIcon("img/purple.png"));
		btnColorBlack 	= new JButton(new ImageIcon("img/black.png"));
		
		pnColor.setLayout(new GridLayout(2, 0));
		
		pnColor.add(btnColorRed);
		pnColor.add(btnColorOrange);
		pnColor.add(btnColorYellow);
		pnColor.add(btnColorGreen);
		pnColor.add(btnColorSky);
		pnColor.add(btnColorBlue);
		pnColor.add(btnColorPink);
		pnColor.add(btnColorPurple);
		pnColor.add(btnColorBlack);
		
		// 6.1 색상 버튼 이벤트
		colorBtnClickEventListener = new ColorBtnClickEvent();
		
		btnColorRed.addMouseListener(colorBtnClickEventListener);
		btnColorOrange.addMouseListener(colorBtnClickEventListener);
		btnColorYellow.addMouseListener(colorBtnClickEventListener);
		btnColorGreen.addMouseListener(colorBtnClickEventListener);
		btnColorSky.addMouseListener(colorBtnClickEventListener);
		btnColorBlue.addMouseListener(colorBtnClickEventListener);
		btnColorPink.addMouseListener(colorBtnClickEventListener);
		btnColorPurple.addMouseListener(colorBtnClickEventListener);
		btnColorBlack.addMouseListener(colorBtnClickEventListener);
		
		// 0. 그림판 정보
		this.setTitle("DrawingBoard_ver7");
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("NEW")) {
			
		} else if (e.getActionCommand().equals("EXIT")) {
			System.exit(0);
		} else {
			fileChooser = new JFileChooser();
			
			fileChooser.setFileFilter(new FileNameExtensionFilter("draw", "board"));
			String FileExtension = ((FileNameExtensionFilter)fileChooser.getFileFilter()).getExtensions()[0];
			
			if (e.getActionCommand().equals("OPEN FILE")) {
				if (fileChooser.showOpenDialog(item2_OpenFile) == JFileChooser.APPROVE_OPTION) {
					String fileName = fileChooser.getSelectedFile().getAbsolutePath();
					
					try {
						objToFile = new ObjSerialToFile1(fileName, shapeList);
						objToFile.readShapeObject();
						
						pnBoard.repaint();
					} catch (Exception err) {
						err.printStackTrace();
					}
				}
			} else if (e.getActionCommand().equals("SAVE")) {
				if (fileChooser.showOpenDialog(item3_Save) == JFileChooser.APPROVE_OPTION) {
					String fileName = fileChooser.getSelectedFile().getAbsolutePath();
					
					try {
						objToFile = new ObjSerialToFile1(fileName + "." + FileExtension, shapeList);
						objToFile.writeShapeObject();
					} catch (Exception err) {
						err.printStackTrace();
					}
				}
			}
		}
	}
	
	// 실제 그림이 그려지는 패널 설정
	class pnDrawingBoard extends JPanel {
		pnDrawingBoard () {
			this.addMouseListener(new MouseAdapter () {
				Point pStart, pEnd;
				
				@Override
				public void mouseClicked(MouseEvent e) {
					pStart = e.getPoint();
					if (btnCurrentClicked == btnFigureSquare) {
						Graphics g = pnBoard.getGraphics();
						g.setColor(colorCurrentClicked);
						g.drawRect(pStart.x - 50, pStart.y - 50, 100, 100);
						
						Shape squareShape = new Square(pStart.x, pStart.y, 100, 100);
						shapeList.add(squareShape);
					} else if (btnCurrentClicked == btnFigureCircle) {
						Graphics g = pnBoard.getGraphics();
						g.setColor(colorCurrentClicked);
						g.drawOval(pStart.x -50, pStart.y - 50, 100, 100);
						
						Shape circleShape = new Circle(pStart.x, pStart.y, 100);
						shapeList.add(circleShape);
					} else if (btnCurrentClicked == btnText) {
						Graphics g = pnBoard.getGraphics();
						String tmp = JOptionPane.showInputDialog("입력할 텍스트");
						Font font = new Font("SansSerif", Font.PLAIN, 20);
						g.setFont(font);
						
						g.setColor(colorCurrentClicked);
						g.drawString(tmp, pStart.x, pStart.y);
						
					}
				}

				@Override
				public void mousePressed(MouseEvent e) {
					pStart = e.getPoint();
					
					if (btnCurrentClicked == btnPen) {
						lineList.add(pStart);
					}
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					pEnd = e.getPoint();
					
					if (btnCurrentClicked == btnFigureLine) {
						Graphics g = pnBoard.getGraphics();
						g.setColor(colorCurrentClicked);
						g.drawLine(pStart.x, pStart.y, pEnd.x, pEnd.y);
						
						Shape lineShape = new Line(pStart.x, pStart.y, pEnd.x, pEnd.y);
						shapeList.add(lineShape);
					} else if (btnCurrentClicked == btnPen) {
						lineList.add(null);
					}
				}
			});
			
			this.addMouseMotionListener(new MouseMotionAdapter () {
				@Override
				public void mouseDragged(MouseEvent e) {
					if (btnCurrentClicked == btnPen) {
						lineList.add(e.getPoint());
						repaint();
					}
				}
			});
		}
		
		public void paint (Graphics g) {
			super.paint(g);
			
			for (int i = 0 ; i < shapeList.size() ; i++) {
				Shape shapeType = (shapeList.get(i));
				
				if (shapeType.getShapeType() == 1) {
					g.setColor(colorCurrentClicked);
					g.drawLine(shapeType.getStartXPosition(), shapeType.getStartYPosition(), ((Line)shapeType).getEndXPosition(), ((Line)shapeType).getEndYPosition());
				} else if (shapeType.getShapeType() == 2) {
					g.setColor(colorCurrentClicked);
					g.drawOval(shapeType.getStartXPosition(), shapeType.getStartYPosition(), ((Circle)shapeType).getRadius(), ((Circle)shapeType).getRadius());
				} else if (shapeType.getShapeType() == 3) {
					g.setColor(colorCurrentClicked);
					g.drawRect(shapeType.getStartXPosition(), shapeType.getStartYPosition(), ((Square)shapeType).getWidth(), ((Square)shapeType).getHeight());
				}
				
			}
			
			g.setColor(colorCurrentClicked);
			
			for (int i = 1 ; i < lineList.size(); i++) {
				if (lineList.get(i-1) == null) {
					continue;
				} else if (lineList.get(i) == null) {
					continue;
				} else {
					g.drawLine((int)lineList.get(i-1).getX(), (int)lineList.get(i-1).getY(),
							(int)lineList.get(i).getX(), (int)lineList.get(i).getY());
				}
			}
		}
	}
	
	// 도형 버튼 클릭 시 이벤트
	class FigureBtnClickEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == btnFigureLine) {
				btnFigureLine.setBackground(Color.CYAN);
			} else if (e.getSource() == btnFigureSquare) {
				btnFigureSquare.setBackground(Color.YELLOW);
			} else if (e.getSource() == btnFigureCircle) {
				btnFigureCircle.setBackground(Color.PINK);
			} else if (e.getSource() == btnPen) {
				btnPen.setBackground(Color.GREEN);
			} else if (e.getSource() == btnText) {
				btnText.setBackground(Color.ORANGE);
			} else if (e.getSource() == btnReset) {
				shapeList.clear();
				lineList.clear();
				pnBoard.repaint();
				pnBoard.setBackground(Color.WHITE);
			}
			
			if (btnCurrentClicked != null) {
				btnCurrentClicked.setBackground(colorOriginalBtn);
			}
			
			btnCurrentClicked = (JButton)e.getSource();
		}	
	}
	
	// 컬러 버튼 클릭 시
	class ColorBtnClickEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == btnColorRed) {
				colorCurrentClicked = Color.RED;
			} else if (e.getSource() == btnColorOrange) {
				colorCurrentClicked = Color.ORANGE;
			} else if (e.getSource() == btnColorYellow) {
				colorCurrentClicked = Color.YELLOW;
			} else if (e.getSource() == btnColorGreen) {
				colorCurrentClicked = Color.GREEN;
			} else if (e.getSource() == btnColorSky) {
				colorCurrentClicked = Color.CYAN;
			} else if (e.getSource() == btnColorBlue) {
				colorCurrentClicked = Color.BLUE;
			} else if (e.getSource() == btnColorPink) {
				colorCurrentClicked = Color.PINK;
			} else if (e.getSource() == btnColorPurple) {
				colorCurrentClicked = Color.MAGENTA;
			} else if (e.getSource() == btnColorBlack) {
				colorCurrentClicked = Color.BLACK;
			}
		}
	}
}

class ObjSerialToFile1 {
	private String					fileName;
	
	private ObjectInputStream		objIn;
	private ObjectOutputStream	objOut;
	private FileInputStream		fileIn;
	private FileOutputStream		fileOut;
	
	public Vector<Shape>			tmpShapeList;
	
	ObjSerialToFile1 (String argFileName, Vector<Shape> argShapeList)  {
		this.fileName 		= argFileName;
		this.tmpShapeList 	= argShapeList;
	}
	
	void writeShapeObject() {
		try {
			fileOut = new FileOutputStream(this.fileName);
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(tmpShapeList);
		} catch (FileNotFoundException err) {
			err.printStackTrace();
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			try {
				fileOut.close();
				objOut.close();
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
	}
	
	void readShapeObject () {
		try {
			fileIn = new FileInputStream(this.fileName);
			objIn = new ObjectInputStream(fileIn);
			tmpShapeList.addAll((Vector<Shape>)objIn.readObject());
		} catch (EOFException err) {
			err.printStackTrace();
		} catch (Exception err) {
			err.printStackTrace();
		} finally {
			try {
				fileIn.close();
				objIn.close();
			} catch (IOException err) {
				err.printStackTrace();
			}
		}
	}
}

public class DrawingBoard_var7 {
	public static void main(String[] args) {
		DrawingB drawingBoard = new DrawingB();
	}
}
