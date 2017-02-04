package com.example.admin.movieplaydome.uitls;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.movieplaydome.R;

public class ComDialog extends Dialog {
    private CommonDialogController mController = null;

    protected ComDialog(Context context) {
        super(context, R.style.common_dialog);
        mController = new CommonDialogController();
    }
    public void setCommnetEditHint(String hint) {
        if (mController != null && hint != null) {
            mController.setCommentEditHint(hint);
        }
    }

    public void setCommentEditLength(int length) {
        if (mController != null) {
            mController.setCommentEditLength(length);
        }
    }

    public void setCommentEditEnabled(boolean enable) {
        if (mController != null) {
            mController.setCommentEditEnabled(enable);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_dialog);
        mController.mTitleView = (TextView) findViewById(R.id.tv_title);
        mController.mContentView = (TextView) findViewById(R.id.tv_content_message);
        mController.mLeftButton = (TextView) findViewById(R.id.tv_left_button);
        mController.mRightButton = (TextView) findViewById(R.id.dialog_right_button);
        mController.mOneButton = (TextView) findViewById(R.id.tv_button);
        mController.mComment = (EditText) findViewById(R.id.comment_message);
        mController.mLayoutOneButtons = (RelativeLayout) findViewById(R.id.mRel_one_button);
        mController.mLayoutTwoButtons = (RelativeLayout) findViewById(R.id.mRel_two_button);
        mController.installView();
    }

    public String getComment() {
        return mController != null ? mController.getComment() : "";
    }

    public static class Builder {
        private CommonDialogParams mParams = null;

        public Builder(Context context) {
            mParams = new CommonDialogParams(context);
        }

        public Builder setContentGravity(int gravity) {
            mParams.mGravity = gravity;
            return this;
        }
        //此参数请直接写sp的数值即可
        public Builder setContentSize(int size) {
            mParams.mSize = size;
            return this;
        }

        public Builder setTitle(String title) {
            mParams.mTitle = title;
            return this;
        }

        public Builder setContent(String content) {
            mParams.mContent = content;
            return this;
        }

        public Builder setOneButtonInterface(String buttonText, View.OnClickListener btn) {
            mParams.mOneButtonMsg = buttonText;
            mParams.mOneButtonInterface = btn;
            return this;
        }

        public Builder setLeftButtonInterface(String leftButtonText, View.OnClickListener left) {
            mParams.mLeftButtonMsg = leftButtonText;
            mParams.mLeftButtonInterface = left;
            return this;
        }

        public Builder setRightButtonInterface(String rightButtonText, View.OnClickListener right) {
            mParams.mRightButtonMsg = rightButtonText;
            mParams.mRightButtonInterface = right;
            return this;
        }

        public ComDialog show() {
            final ComDialog dialog = new ComDialog(mParams.mContext);
            mParams.apply(dialog.mController);
            dialog.show();
            return dialog;
        }

        public Builder setNeedComment(boolean need) {
            mParams.mNeedMessage = need;
            return this;
        }

        public Builder setTouchAble(boolean touchAble) {
            mParams.mTouchAble = touchAble;
            return this;
        }

        private static class CommonDialogParams {
            public String mTitle = "";
            public String mContent = "";
            public int mGravity;
            public int mSize;
            public String mLeftButtonMsg = "";
            public String mRightButtonMsg = "";
            public String mOneButtonMsg = "";
            public View.OnClickListener mLeftButtonInterface = null;
            public View.OnClickListener mRightButtonInterface = null;
            public View.OnClickListener mOneButtonInterface = null;
            public boolean mNeedMessage = false;
            public Context mContext = null;
            public boolean mTouchAble = false;

            public CommonDialogParams(Context context) {
                mContext = context;
            }

            public void apply(CommonDialogController controller) {
                controller.mTitle = mTitle;
                controller.mContent = mContent;
                controller.mGravity = mGravity;
                controller.mSize = mSize;
                controller.mOneButtonMsg = mOneButtonMsg;
                controller.mLeftButtonMsg = mLeftButtonMsg;
                controller.mRightButtonMsg = mRightButtonMsg;
                controller.mLeftButtonInterface = mLeftButtonInterface;
                controller.mRightButtonInterface = mRightButtonInterface;
                controller.mOneButtonInterface = mOneButtonInterface;
                controller.mNeedComment = mNeedMessage;
                controller.mTouchable = mTouchAble;
            }
        }
    }

    private class CommonDialogController {
        public String mTitle = "";
        public String mContent = "";
        public int mGravity;
        public int mSize;
        public String mLeftButtonMsg = "";
        public String mRightButtonMsg = "";
        public String mOneButtonMsg = "";
        public boolean mNeedComment = false;
        public View.OnClickListener mLeftButtonInterface = null;
        public View.OnClickListener mRightButtonInterface = null;
        public View.OnClickListener mOneButtonInterface = null;
        private TextView mTitleView = null;
        private TextView mContentView = null;
        private TextView mOneButton = null;
        private TextView mLeftButton = null;
        private TextView mRightButton = null;
        private EditText mComment = null;

        private RelativeLayout mLayoutOneButtons = null;
        private RelativeLayout mLayoutTwoButtons = null;

        private boolean mTouchable = false;

        private void setCommentEditLength(int size) {
            if (mComment != null) {
                mComment.setFilters(new InputFilter[]{new InputFilter.LengthFilter(size)});
            }
        }

        private String getComment() {
            if (mComment != null && mComment.getText() != null) {
                return mComment.getText().toString();
            }
            return "";
        }

        private void setCommentEditEnabled(boolean enable) {
            if (mComment != null) {
                mComment.setEnabled(enable);
                mComment.requestFocus();
            }
        }

        private void setCommentEditHint(String hint) {
            if (mComment != null) {
                mComment.setHint(hint);
            }
        }

        private void installView() {
            //标题栏
            if (mTitleView != null) {
                if (mTitle == null || mTitle.equalsIgnoreCase("")) {
                    mTitleView.setVisibility(View.GONE);
                } else {
                    mTitleView.setVisibility(View.VISIBLE);
                    mTitleView.setText(mTitle);
                }
            }
            //对话框内容
            if (mContentView != null) {
                if (TextUtils.isEmpty(mContent)) {
                    mContentView.setVisibility(View.GONE);
                } else {
                    mContentView.setVisibility(View.VISIBLE);
                    mContentView.setText(mContent);
                    if (mGravity > 0) {
                        mContentView.setGravity(mGravity);
                    }

                    if (mSize > 0) {
                        mContentView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mSize);
                    }
                }
            }

            if (mOneButton != null && !TextUtils.isEmpty(mOneButtonMsg)) {
                mLayoutOneButtons.setVisibility(View.VISIBLE);
                mLayoutTwoButtons.setVisibility(View.GONE);
                mOneButton.setText(mOneButtonMsg);
                mOneButton.setOnClickListener(mOneButtonInterface);
            } else {
                mLayoutTwoButtons.setVisibility(View.VISIBLE);
                mLayoutOneButtons.setVisibility(View.GONE);
                if (mLeftButton != null) {
                    if (mLeftButtonMsg == null || mLeftButtonMsg.equalsIgnoreCase("")
                            || mLeftButtonInterface == null) {
                        mLeftButton.setVisibility(View.GONE);
                    } else {
                        mLeftButton.setText(mLeftButtonMsg);
                        mLeftButton.setOnClickListener(mLeftButtonInterface);
                    }
                }
                if (mRightButton != null) {
                    if (mRightButtonMsg == null || mRightButtonMsg.equalsIgnoreCase("")
                            || mRightButtonInterface == null) {
                        mRightButton.setVisibility(View.GONE);
                    } else {
                        mRightButton.setText(mRightButtonMsg);
                        mRightButton.setOnClickListener(mRightButtonInterface);
                    }
                }
            }

            if (mComment != null) {
                if (mNeedComment) {
                    mComment.setVisibility(View.VISIBLE);
                } else {
                    mComment.setVisibility(View.GONE);
                }
            }
            setCanceledOnTouchOutside(mTouchable);
            setCancelable(mTouchable);
        }
    }
}
