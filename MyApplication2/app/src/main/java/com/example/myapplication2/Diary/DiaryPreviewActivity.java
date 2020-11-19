package com.example.myapplication2.Diary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication2.Diary.DiaryHowPackage.DiaryHowActivity;
import com.example.myapplication2.DiaryValue;
import com.example.myapplication2.R;

import java.util.Calendar;


public class DiaryPreviewActivity extends AppCompatActivity{

    public static String total = "";
    public String totalPlus = "";
    public String finalTotal= "";
    public TextView textView;
    private static String a ="";
    private static String b;
    private Button btn_SaveEdit;
    private ImageButton imBtnBack;
    private String noWhat = "";
//    public String edit = "";
    private String tag1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivty_diarypreview);

        Bundle tag = getIntent().getExtras();
        tag1 = tag.getString("1");
        textView = findViewById(R.id.txtPreview);
//        edit = getIntent().getStringExtra("Edit");


        // 回上一頁
        imBtnBack = findViewById(R.id.imbtnReturn);
        imBtnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (tag1.equals("DiaryTagActivity")) {
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryTagActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
                } else if (tag1.equals("DiaryWhatActivity")) {
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhatActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
                } else if (tag1.equals("DiaryWhyActivity")) {
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhyActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
                } else if (tag1.equals("DiaryWhereActivity")) {
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
                } else if (tag1.equals("DiaryWhenActivity")) {
                    DiaryValue.Time = "";
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
                } else if (tag1.equals("DiaryTravelWhenActivity")) {
                    DiaryValue.Time = "";
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryTravelWhenActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
                } else if (tag1.equals("DiaryTravelWhereActivity")) {
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryTravelWhereActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
                } else if (tag1.equals("DiaryWhoActivity")) {
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhoActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
                } else if (tag1.equals("DiaryHowActivity")) {
                    // 很重要
                    DiaryValue.howCount = 0;
                    DiaryValue.Eye_Count = 0;
                    DiaryValue.Mouth_Count = 0;
                    DiaryValue.Smell_Count = 0;
                    Intent intent = new Intent(DiaryPreviewActivity.this, DiaryHowActivity.class);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                    DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
                }
            }
        });


        // total
        final Guidor guidor = new Guidor(getApplicationContext(), "diary.db", null, 1);
        String a = "";
        if (DiaryValue.txtTag.equals("")) {
            guidor.clearDiary();
            guidor.setMood(DiaryValue.txtMood);
            a = guidor.getDiary();
        } else if (DiaryValue.txtTag.equals("美食")) {
            guidor.clearDiary();
            if (DiaryValue.txtWhat.equals("")) {
                if (DiaryValue.txtWhy.equals("")) {
                    if (DiaryValue.txtWhere.equals("")) {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    }
                } else {
                    if (DiaryValue.txtWhere.equals("")) {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    }
                }

                noWhat = "美食無What";
                DiaryValue.txtWhat = noWhat;
            } else {
                if (DiaryValue.txtWhy.equals("")) {
                    if (DiaryValue.txtWhere.equals("")) {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }
                } else {
                    if (DiaryValue.txtWhere.equals("")) {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("味覺")) {
                                        for (int j = 0; j < DiaryValue.Mouth_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Mouth[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            }
        } else if (DiaryValue.txtTag.equals("購物")) {
            guidor.clearDiary();
            if (DiaryValue.txtWhen.equals("")) {
                if (DiaryValue.txtWho.equals("")) {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhat(DiaryValue.txtWhat);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    }
                } else {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            } else {
                if (DiaryValue.txtWho.equals("")) {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    }
                } else {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhat.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                                noWhat = "購物無What";
                                DiaryValue.txtWhat = noWhat;
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            }
        } else if (DiaryValue.txtTag.equals("休閒娛樂")) {
            guidor.clearDiary();
            if (DiaryValue.txtWhy.equals("")) {
                if (DiaryValue.txtWhat.equals("")) {
                    if (DiaryValue.txtWhen.equals("")) {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    }
                    noWhat = "休閒娛樂無What";
                    DiaryValue.txtWhat = noWhat;
                } else {
                    if (DiaryValue.txtWhen.equals("")) {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            } else {
                if (DiaryValue.txtWhat.equals("")) {
                    if (DiaryValue.txtWhen.equals("")) {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    }
                    noWhat = "休閒娛樂無What";
                    DiaryValue.txtWhat = noWhat;
                } else {
                    if (DiaryValue.txtWhen.equals("")) {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhere.equals("")) {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWho.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            }
        } else if (DiaryValue.txtTag.equals("戀愛")) {
            guidor.clearDiary();
            if (DiaryValue.txtWhere.equals("")) {
                if (DiaryValue.txtWhat.equals("")) {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWho.equals("")) {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWho.equals("")) {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            }
                        }
                    }
                    noWhat = "戀愛無What";
                    DiaryValue.txtWhat = noWhat;
                } else {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWho.equals("")) {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWho.equals("")) {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhat(DiaryValue.txtWhat);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            } else {
                if (DiaryValue.txtWhat.equals("")) {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWho.equals("")) {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWho.equals("")) {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    }
                    noWhat = "戀愛無What";
                    DiaryValue.txtWhat = noWhat;
                } else {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWho.equals("")) {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWho.equals("")) {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhen.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            }
        } else if (DiaryValue.txtTag.equals("旅遊")) {
            guidor.clearDiary();
            if (DiaryValue.txtWho.equals("")) {
                if (DiaryValue.txtWhat.equals("")) {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    }
                    noWhat = "旅遊無What";
                    DiaryValue.txtWhat = noWhat;
                } else {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            } else {
                if (DiaryValue.txtWhat.equals("")) {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                a = guidor.getDiary();
                            }
                        }
                    }
                    noWhat = "旅遊無What";
                    DiaryValue.txtWhat = noWhat;
                } else {
                    if (DiaryValue.txtWhy.equals("")) {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    } else {
                        if (DiaryValue.txtWhen.equals("")) {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        } else {
                            if (DiaryValue.txtWhere.equals("")) {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            } else {
                                guidor.setMood(DiaryValue.txtMood);
                                guidor.setTag(DiaryValue.txtTag);
                                guidor.setWhen(DiaryValue.txtWhen);
                                guidor.setWho(DiaryValue.txtWho);
                                guidor.setWhy(DiaryValue.txtWhy);
                                guidor.setWhere(DiaryValue.txtWhere);
                                guidor.setWhat(DiaryValue.txtWhat);
                                for (int i = 0; i < DiaryValue.howCount; i++) {
                                    if (DiaryValue.txtHow_choose[i].equals("視覺")) {
                                        for (int j = 0; j < DiaryValue.Eye_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Eye[j]);
                                        }
                                    } else if (DiaryValue.txtHow_choose[i].equals("嗅覺")) {
                                        for (int j = 0; j < DiaryValue.Smell_Count; j++) {
                                            guidor.setHow(DiaryValue.txtHow_choose[i], DiaryValue.txtHow_food_Smell[j]);
                                        }
                                    }
                                }
                                a = guidor.getDiary();
                            }
                        }
                    }
                }
            }
        }

        guidor.clearDiary();
        Calendar c = Calendar.getInstance();
        totalPlus = (a + "\n\n");
        String time = "";
        if (DiaryValue.Time.equals("")) {
            if (DiaryValue.EndTime.equals("")) {
                time = "時間"+c.get(Calendar.YEAR)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.DAY_OF_MONTH)+"\n";
            } else {
                time = "結束時間"+DiaryValue.EndTime+"\n";
            }
        } else {
            if (DiaryValue.EndTime.equals("")) {
                time = "時間"+DiaryValue.Time+"\n";
            } else {
                time = "開始時間"+DiaryValue.Time+"\n結束時間"+DiaryValue.EndTime+"\n";
            }
        }

        finalTotal = total + time+totalPlus;

        textView.setText(finalTotal);


        // 返回主題頁
        final TextView mChangeTag = findViewById(R.id.btn_changeTag);
        mChangeTag.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                total = textView.getText().toString();
                // 很重要
                DiaryValue.howCount = 0;
                DiaryValue.Eye_Count = 0;
                DiaryValue.Mouth_Count = 0;
                DiaryValue.Smell_Count = 0;
                DiaryValue.Time = "";
                DiaryValue.EndTime = "";
                if (noWhat.equals("")){
                    if(DiaryValue.firstWhat.equals("")){
                        DiaryValue.firstWhat = DiaryValue.txtWhat;
                    }
                }else {
                    if(DiaryValue.firstWhat.equals("")){
                        DiaryValue.firstWhat = noWhat;
                    }
                }
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryTagActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                startActivity(intent, options.toBundle());
            }
        });



        // 結束
        final TextView mEnd = findViewById(R.id.btn_end);
        mEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryEndActivity.class);
                intent.putExtra("total",textView.getText().toString());
                intent.putExtra("1",tag1);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                startActivity(intent,options.toBundle());
            }
        });

    }

    // 擋住手機上回上一頁鍵
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自動產生的方法 Stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            if (tag1.equals("DiaryTagActivity")) {
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryTagActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
            } else if (tag1.equals("DiaryWhatActivity")) {
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhatActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
            } else if (tag1.equals("DiaryWhyActivity")) {
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhyActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
            } else if (tag1.equals("DiaryWhereActivity")) {
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
            } else if (tag1.equals("DiaryWhenActivity")) {
                DiaryValue.Time = "";
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhenActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
            } else if (tag1.equals("DiaryTravelWhenActivity")) {
                DiaryValue.Time = "";
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryTravelWhenActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
            } else if (tag1.equals("DiaryTravelWhereActivity")) {
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryTravelWhereActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
            } else if (tag1.equals("DiaryWhoActivity")) {
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryWhoActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
            } else if (tag1.equals("DiaryHowActivity")) {
                // 很重要
                DiaryValue.howCount = 0;
                DiaryValue.Eye_Count = 0;
                DiaryValue.Mouth_Count = 0;
                DiaryValue.Smell_Count = 0;
                Intent intent = new Intent(DiaryPreviewActivity.this, DiaryHowActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DiaryPreviewActivity.this);
                DiaryPreviewActivity.this.startActivity(intent, options.toBundle());
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
